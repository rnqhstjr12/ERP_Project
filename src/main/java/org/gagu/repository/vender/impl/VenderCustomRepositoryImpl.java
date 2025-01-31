package org.gagu.repository.vender.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.gagu.dto.vender.VenderResponse;
import org.gagu.entity.vender.QVender;
import org.gagu.repository.vender.VenderCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class VenderCustomRepositoryImpl implements VenderCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<VenderResponse> getVenderList(int size, int page) {
        QVender qVender = QVender.vender;

        QueryResults<VenderResponse> results = jpaQueryFactory
                .select(Projections.constructor(
                        VenderResponse.class,
                        qVender.venderId,
                        qVender.venderName,
                        qVender.companyNum,
                        qVender.phone,
                        qVender.address
                ))
                .from(qVender)
                .offset((long) size * page)
                .limit(size)
                .fetchResults();
        // change result into instance
        List<VenderResponse> content = results.getResults();
        long total = results.getTotal();
        PageRequest pageRequest = PageRequest.of(page, size);
        return new PageImpl<>(content, pageRequest, total);
    }
}