package com.corporatedoc.repo;

import com.corporatedoc.model.Users;
import com.corporatedoc.model.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    List<Users> findAllByOrderByRole();

    List<Users> findAllByTertiary_MaritalAndTertiary_OriginAndTertiary_CitizenshipAndTertiary_RetireeAndTertiary_ConscriptedAndTertiary_Disability(Marital marital, Origin origin, Citizenship citizenship, YesNo retiree, YesNo conscripted, Disability disability);

    List<Users> findAllByRole(Role role);
}