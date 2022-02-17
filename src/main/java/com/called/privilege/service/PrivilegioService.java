package com.called.privilege.service;

import com.called.privilege.model.PrivilegioModel;

//import java.util.List;
import java.util.Optional;

public interface PrivilegioService {
    Optional<PrivilegioModel> findPrivilegioByNome(String nome);

//    List<PrivilegioModel> findPrivilegiosByOrderByNomeAsc();

    void savePrivilegio(PrivilegioModel privilegioModel);

}
