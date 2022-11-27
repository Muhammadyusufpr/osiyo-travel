package com.osiyotravel.repository.filter;

import com.osiyotravel.dto.request.ClientFilterDTO;
import com.osiyotravel.mapper.ClientMapperDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientFilterRepository {
    private final EntityManager entityManager;

    public ClientMapperDTO filter(ClientFilterDTO dto, int page, int size) {
        StringBuilder sqlQuery = new StringBuilder();

        String sql = "select new com.osiyotravel.mapper.ClientMapperDTO( client.id as id, client.name as name," +
                " client.surname as surname, client.phone as phone, client.gender as gender, client.attachId as attachId," +
                " fl.id as filialId, client.price as price )" +
                "  from ClientEntity as client" +
                " inner join client.filial as fl " +
                " where client.visible = true ";

        StringBuilder countSql = new StringBuilder("select count(client.id) " +
                " from ClientEntity as client " +
                " inner join client.filial as fl " +
                "  where client.visible = true ");

        Map<String, Object> param = new HashMap<>();
        sqlQuery.append(sql);

        if (Optional.ofNullable(dto).isPresent()) {

            if (Optional.ofNullable(dto.getText()).isPresent()) {
                sqlQuery.append(" and client.name like :text ");
                countSql.append(" and client.name like :text ");
                sqlQuery.append(" or client.surname like :text ");
                countSql.append(" or client.surname like :text ");
                sqlQuery.append(" or client.phone like :text ");
                countSql.append(" or client.phone like :text ");
                param.put("text", "%".concat(dto.getText().concat("%")));
            }

            if (Optional.ofNullable(dto.getFilialId()).isPresent()){
                sqlQuery.append(" and client.filialId = :filialId");
                param.put("filialId",dto.getFilialId());
            }
        }
        Query query = entityManager.createQuery(sqlQuery.toString(), ClientMapperDTO.class);
        Query countQuery = entityManager.createQuery(countSql.toString(), Long.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        param.forEach(query::setParameter);
        param.forEach(countQuery::setParameter);

        List<ClientMapperDTO> list = query.getResultList();
        return new ClientMapperDTO(list, (Long) countQuery.getResultList().get(0));
    }

}
