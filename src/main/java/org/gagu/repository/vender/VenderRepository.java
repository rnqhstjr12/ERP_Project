package org.gagu.repository.vender;

import org.gagu.dto.vender.UpdateVender;
import org.gagu.dto.vender.VenderResponse;
import org.gagu.entity.vender.Vender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VenderRepository extends JpaRepository<Vender, Integer>, VenderCustomRepository {
    @Query("SELECT NEW org.gagu.dto.vender.VenderResponse(" +
            "v.venderId, " +
            "v.venderName, " +
            "v.companyNum, " +
            "v.phone, " +
            "v.address) " +
            "FROM " +
            "Vender v " )
    VenderResponse findAll(@Param("venderId") int venderId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Vender v SET " +
            "v.venderName = :#{#request.venderName}, " +
            "v.companyNum = :#{#request.companyNum}, " +
            "v.phone = :#{#request.phone}, " +
            "v.fax = :#{#request.fax}, " +
            "v.address = :#{#request.address}, " +
            "v.headName = :#{#request.headName}, " +
            "v.type = :#{#request.type}, " +
            "v.note = :#{#request.note}, " +
            "v.bigType = :#{#request.bigType}, " +
            "v.smallType = :#{#request.smallType} " +
            "WHERE v.venderId = :venderId")
    void updateVender(@Param("venderId") int venderId, @Param("request")UpdateVender request);
}