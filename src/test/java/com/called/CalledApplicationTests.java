package com.called;

import com.called.collaborator.technician.service.TecnicoService;
import com.called.equipment.model.EquipamentoModel;
import com.called.location.model.LocalModel;
import com.called.partner.manager.service.GestorUnidadeService;
import com.called.privilege.model.PrivilegioModel;
import com.called.privilege.service.PrivilegioService;
import com.called.rules.service.RegraService;
import com.called.unity.service.UnidadeService;
import com.called.user.model.UsuarioModel;
import com.called.user.service.UsuarioService;
import com.called.called.model.ChamadoModel;
import com.called.called.service.ChamadoService;
import com.called.collaborator.technician.model.TecnicoModel;
import com.called.equipment.service.EquipamentoService;
import com.called.location.service.LocalService;
import com.called.partner.manager.model.GestorUnidadeModel;
import com.called.rules.model.RegraModel;
import com.called.unity.model.UnidadeModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.Instant;
import java.util.List;

@SpringBootTest
class CalledApplicationTests {

    @Autowired
    private PrivilegioService privilegioService;

    @Autowired
    private RegraService regraService;

    @Autowired
    private LocalService localService;

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GestorUnidadeService gestorUnidadeService;

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private EquipamentoService equipamentoService;

    @Test
    @Order(1)
    public void privilegios() {
        PrivilegioModel chamadoAbrir = new PrivilegioModel();
        chamadoAbrir.setNome("CHAMADO-CRIAR");
        PrivilegioModel chamadoEditar = new PrivilegioModel();
        chamadoEditar.setNome("CHAMADO-EDITAR");
        PrivilegioModel chamadoEncaminhar = new PrivilegioModel();
        chamadoEncaminhar.setNome("CHAMADO-ENCAMINHAR");
        PrivilegioModel chamadoAtender = new PrivilegioModel();
        chamadoAtender.setNome("CHAMADO-ATENDER");
        PrivilegioModel chamadoFechar = new PrivilegioModel();
        chamadoFechar.setNome("CHAMADO-ENCERRAR");

        privilegioService.savePrivilegio(chamadoAbrir);
        privilegioService.savePrivilegio(chamadoEditar);
        privilegioService.savePrivilegio(chamadoEncaminhar);
        privilegioService.savePrivilegio(chamadoAtender);
        privilegioService.savePrivilegio(chamadoFechar);

        PrivilegioModel osAbrir = new PrivilegioModel();
        osAbrir.setNome("OS-CRIAR");
        PrivilegioModel osEditar = new PrivilegioModel();
        osEditar.setNome("OS-EDITAR");
        PrivilegioModel osAssinar = new PrivilegioModel();
        osAssinar.setNome("OS-ASSINAR");
        PrivilegioModel osFechar = new PrivilegioModel();
        osFechar.setNome("OS-ENCERRAR");

        privilegioService.savePrivilegio(osAbrir);
        privilegioService.savePrivilegio(osEditar);
        privilegioService.savePrivilegio(osAssinar);
        privilegioService.savePrivilegio(osFechar);
    }

    @Test
    @Order(2)
    public void regras() {

        String regraTecnico = "TECNICO";
        List<String> privilegiosTecnico = List.of("CHAMADO-ATENDER", "OS-CRIAR", "OS-EDITAR", "OS-ASSINAR", "OS-ENCERRAR");

        regraService.findRegraByNome(regraTecnico).ifPresentOrElse((regra) ->
                System.out.println(regra.getNome() + " já cadastrada!"), () -> {
            RegraModel tecnicoRegra = new RegraModel();
            tecnicoRegra.setNome(regraTecnico);
            for (String privilegio : privilegiosTecnico) {
                privilegioService.findPrivilegioByNome(privilegio).ifPresent((pri) -> tecnicoRegra.getPrivilegios().add(pri));
            }
            regraService.saveRegra(tecnicoRegra);
        });

        String regraGestorTecnico = "GESTOR-TECNICO";
        List<String> privilegiosGestorTecnico = List.of("CHAMADO-CRIAR", "CHAMADO-EDITAR", "CHAMADO-ENCAMINHAR",
                "CHAMADO-ATENDER", "CHAMADO-ENCERRAR");

        regraService.findRegraByNome(regraGestorTecnico).ifPresentOrElse((regra) ->
                System.out.println(regra.getNome() + " já cadastrada!"), () -> {
            RegraModel gestorRegra = new RegraModel();
            gestorRegra.setNome(regraGestorTecnico);
            for (String privilegio : privilegiosGestorTecnico) {
                privilegioService.findPrivilegioByNome(privilegio).ifPresent((pri) -> gestorRegra.getPrivilegios().add(pri));
            }
            regraService.saveRegra(gestorRegra);
        });

        String regraGestorUnidade = "GESTOR-UNIDADE";
        List<String> privilegiosGestorUnidade = List.of("CHAMADO-CRIAR");

        regraService.findRegraByNome(regraGestorUnidade).ifPresentOrElse((regra) ->
                System.out.println(regra.getNome() + " já cadastrada!"), () -> {
            RegraModel gestorUnidadeRR = new RegraModel();
            gestorUnidadeRR.setNome(regraGestorUnidade);
            for (String privilegio : privilegiosGestorUnidade) {
                privilegioService.findPrivilegioByNome(privilegio).ifPresent((pri) -> gestorUnidadeRR.getPrivilegios().add(pri));
            }
            regraService.saveRegra(gestorUnidadeRR);
        });

        String regraSolicitante = "SOLICITANTE-UNIDADE";
        List<String> privilegiosSolicitanteUnidade = List.of("CHAMADO-CRIAR");

        regraService.findRegraByNome(regraSolicitante).ifPresentOrElse((regra) ->
                System.out.println(regra.getNome() + " já cadastrada!"), () -> {
            RegraModel regraSolici = new RegraModel();
            regraSolici.setNome(regraSolicitante);
            for (String privilegio : privilegiosSolicitanteUnidade) {
                privilegioService.findPrivilegioByNome(privilegio).ifPresent((pri) -> regraSolici.getPrivilegios().add(pri));
            }
            regraService.saveRegra(regraSolici);
        });

    }

    @Test
    @Order(3)
    public void locais() {
        LocalModel localA = new LocalModel();
        localA.setNome("RORAIMA");
        localA.setZona("America/Boa_Vista");

        LocalModel localB = new LocalModel();
        localB.setNome("AMAZONAS");
        localB.setZona("America/Manaus");

        LocalModel localC = new LocalModel();
        localC.setNome("ACRE");
        localC.setZona("America/Porto_Acre");

        localService.saveLocal(localA);
        localService.saveLocal(localB);
        localService.saveLocal(localC);
    }

    @Test
    @Order(4)
    public void unidades() {

        UnidadeModel unidade1 = new UnidadeModel();
        unidade1.setNome("HGR");
        localService.findLocalByNome("RORAIMA").ifPresent(local -> {
            unidade1.setLocal(local);
            unidadeService.saveUnidade(unidade1);
        });

        UnidadeModel unidade2 = new UnidadeModel();
        unidade2.setNome("HCM");
        localService.findLocalByNome("RORAIMA").ifPresent(local -> {
            unidade2.setLocal(local);
            unidadeService.saveUnidade(unidade2);
        });


        UnidadeModel unidade3 = new UnidadeModel();
        unidade3.setNome("HCA");
        localService.findLocalByNome("RORAIMA").ifPresent(local -> {
            unidade3.setLocal(local);
            unidadeService.saveUnidade(unidade3);
        });

        UnidadeModel unidade4 = new UnidadeModel();
        unidade4.setNome("FHAJ");
        localService.findLocalByNome("AMAZONAS").ifPresent(local -> {
            unidade4.setLocal(local);
            unidadeService.saveUnidade(unidade4);
        });

        UnidadeModel unidade5 = new UnidadeModel();
        unidade5.setNome("HSA");
        localService.findLocalByNome("AMAZONAS").ifPresent(local -> {
            unidade5.setLocal(local);
            unidadeService.saveUnidade(unidade5);
        });

        UnidadeModel unidade6 = new UnidadeModel();
        unidade6.setNome("FUNDACRE");
        localService.findLocalByNome("ACRE").ifPresent(local -> {
            unidade6.setLocal(local);
            unidadeService.saveUnidade(unidade6);
        });
    }

    @Test
    @Order(5)
    public void equipamentos() {

        EquipamentoModel equi1 = new EquipamentoModel();
        EquipamentoModel equi2 = new EquipamentoModel();
        EquipamentoModel equi3 = new EquipamentoModel();
        EquipamentoModel equi4 = new EquipamentoModel();

        unidadeService.findUnidadeByNome("HGR").ifPresent((u) -> {
            equi1.setDescricao("TOMOGRAFO");
            equi2.setDescricao("RAIO-X PORTATIL");
            equi1.setUnidade(u);
            equi2.setUnidade(u);
            u.getEquipamentos().add(equi1);
            u.getEquipamentos().add(equi2);
            unidadeService.saveUnidade(u);

        });

        unidadeService.findUnidadeByNome("FUNDACRE").ifPresent((u) -> {
            equi3.setDescricao("ARCO CIRURGICO");
            equi4.setDescricao("RAIO-X");
            equi3.setUnidade(u);
            equi4.setUnidade(u);
            u.getEquipamentos().add(equi3);
            u.getEquipamentos().add(equi4);
            unidadeService.saveUnidade(u);
        });
    }

    @Test
    @Order(6)
    public void usuarios() {

        UsuarioModel lucas = new UsuarioModel();
        lucas.setNome("Miguel");
        lucas.setEmail("miguel@amplomed.com");
        lucas.setSenha("senha");
        regraService.findRegraByNome("TECNICO").ifPresent(regra ->
                localService.findLocalByNome("RORAIMA").ifPresent(local -> {
                    lucas.setRegra(regra);
                    lucas.setLocal(local);
                    usuarioService.saveUsuario(lucas);
                }));

        UsuarioModel daniel = new UsuarioModel();
        daniel.setNome("Daniel");
        daniel.setEmail("daniel@amplomed.com");
        daniel.setSenha("senha");
        regraService.findRegraByNome("TECNICO").ifPresent(regra ->
                localService.findLocalByNome("RORAIMA").ifPresent(local -> {
                    daniel.setRegra(regra);
                    daniel.setLocal(local);
                    usuarioService.saveUsuario(daniel);
                }));

        UsuarioModel mark = new UsuarioModel();
        mark.setNome("Mark");
        mark.setEmail("mark@amplomed.com");
        mark.setSenha("senha");
        regraService.findRegraByNome("TECNICO").ifPresent((regra ->
                localService.findLocalByNome("AMAZONAS").ifPresent((local -> {
                    mark.setRegra(regra);
                    mark.setLocal(local);
                    usuarioService.saveUsuario(mark);
                }))));


        UsuarioModel caleb = new UsuarioModel();
        caleb.setNome("Caleb");
        caleb.setEmail("caleb@amplomed.com");
        caleb.setSenha("senha");
        regraService.findRegraByNome("TECNICO").ifPresent((regra ->
                localService.findLocalByNome("ACRE").ifPresent((local -> {
                    caleb.setRegra(regra);
                    caleb.setLocal(local);
                    usuarioService.saveUsuario(caleb);
                }))));

        UsuarioModel gestorHgr = new UsuarioModel();
        gestorHgr.setNome("Gestor HGR");
        gestorHgr.setEmail("gestorhgr@hgr.com");
        gestorHgr.setSenha("senha");
        regraService.findRegraByNome("GESTOR-UNIDADE").ifPresent((regra ->
                localService.findLocalByNome("RORAIMA").ifPresent((local -> {
                    gestorHgr.setRegra(regra);
                    gestorHgr.setLocal(local);
                    usuarioService.saveUsuario(gestorHgr);
                }))));

        UsuarioModel gestorFundacre = new UsuarioModel();
        gestorFundacre.setNome("Gestor FUNDACRE");
        gestorFundacre.setEmail("gestorfundacre@fundacre.com");
        gestorFundacre.setSenha("senha");
        regraService.findRegraByNome("GESTOR-UNIDADE").ifPresent((regra ->
                localService.findLocalByNome("ACRE").ifPresent((local -> {
                    gestorFundacre.setRegra(regra);
                    gestorFundacre.setLocal(local);
                    usuarioService.saveUsuario(gestorFundacre);
                }))));

    }

    @Test
    @Order(7)
    public void gestores() {
        String email1 = "gestorhgr@hgr.com";
        gestorUnidadeService.findGestorByEmail(email1).ifPresentOrElse((g) ->
                System.out.println(g.getUsuario().getNome() + " ja cadastrado como gestor"), () ->
                usuarioService.findUsuarioByEmail(email1).ifPresent((u) -> {
                    GestorUnidadeModel gestor = new GestorUnidadeModel();
                    gestor.setUsuario(u);
                    gestorUnidadeService.saveGestor(gestor);
                }));


        String email4 = "gestorfundacre@fundacre.com";
        gestorUnidadeService.findGestorByEmail(email4).ifPresentOrElse((g) ->
                System.out.println(g.getUsuario().getNome() + " ja cadastrado como gestor"), () ->
                usuarioService.findUsuarioByEmail(email4).ifPresent((u) -> {
                    GestorUnidadeModel gestor = new GestorUnidadeModel();
                    gestor.setUsuario(u);
                    gestorUnidadeService.saveGestor(gestor);
                }));
    }

    @Test
    @Order(8)
    public void tecnicos() {
        String tec1 = "mark@amplomed.com";
        usuarioService.findUsuarioByEmail(tec1).ifPresent((u) ->
                tecnicoService.findByTecnicoByEmail(u.getEmail()).ifPresentOrElse((x) ->
                        System.out.println(x.getUsuario().getNome() + " ja cadastrado como técnico"), () -> {
                    TecnicoModel tecnicoModel = new TecnicoModel();
                    tecnicoModel.setUsuario(u);
                    tecnicoService.saveTecnico(tecnicoModel);
                }));
        String tec2 = "daniel@amplomed.com";
        usuarioService.findUsuarioByEmail(tec2).ifPresent((u) ->
                tecnicoService.findByTecnicoByEmail(u.getEmail()).ifPresentOrElse((x) ->
                        System.out.println(x.getUsuario().getNome() + " ja cadastrado como técnico"), () -> {
                    TecnicoModel tecnicoModel = new TecnicoModel();
                    tecnicoModel.setUsuario(u);
                    tecnicoService.saveTecnico(tecnicoModel);
                }));
        String tec3 = "caleb@amplomed.com";
        usuarioService.findUsuarioByEmail(tec3).ifPresent((u) ->
                tecnicoService.findByTecnicoByEmail(u.getEmail()).ifPresentOrElse((x) ->
                        System.out.println(x.getUsuario().getNome() + " ja cadastrado como técnico"), () -> {
                    TecnicoModel tecnicoModel = new TecnicoModel();
                    tecnicoModel.setUsuario(u);
                    tecnicoService.saveTecnico(tecnicoModel);
                }));
        String tec4 = "miguel@amplomed.com";
        usuarioService.findUsuarioByEmail(tec4).ifPresent((u) ->
                tecnicoService.findByTecnicoByEmail(u.getEmail()).ifPresentOrElse((x) ->
                        System.out.println(x.getUsuario().getNome() + " ja cadastrado como técnico"), () -> {
                    TecnicoModel tecnicoModel = new TecnicoModel();
                    tecnicoModel.setUsuario(u);
                    tecnicoService.saveTecnico(tecnicoModel);
                }));
    }

    @Test
    @Order(9)
    public void alocarUnidadeParaGestor() {

        String email1 = "gestorfundacre@fundacre.com";
        String unidade1 = "FUNDACRE";

        gestorUnidadeService.findGestorByEmail(email1).ifPresent((gestor) ->
                unidadeService.findUnidadeByNome(unidade1).ifPresent((uni) -> {
                    if (!gestor.getUnidades().contains(uni) && gestor.getUsuario().getLocal().equals(uni.getLocal())) {
                        gestor.getUnidades().add(uni);
                        gestorUnidadeService.saveGestor(gestor);
                    } else {
                        System.out.println("Contem a unidade");
                    }
                }));
        String email2 = "gestorhgr@hgr.com";
        String unidade2 = "HGR";

        gestorUnidadeService.findGestorByEmail(email2).ifPresent((gestor) ->
                unidadeService.findUnidadeByNome(unidade2).ifPresent((uni) -> {
                    if (!gestor.getUnidades().contains(uni) && gestor.getUsuario().getLocal().equals(uni.getLocal())) {
                        gestor.getUnidades().add(uni);
                        gestorUnidadeService.saveGestor(gestor);
                    } else {
                        System.out.println("Contem a unidade");
                    }
                }));
    }

    int cont = 0;

    @Test
    @Order(10)
    public void chamados() {
        String email = "gestorfundacre@fundacre.com";
        String uuid = "600c58eb-370c-4ebe-8e40-d79f72e364d2";

        gestorUnidadeService.findGestorByEmail(email).ifPresent((gestor) ->
                equipamentoService.findByCodigo(uuid).ifPresent((equipamento) -> {

                    gestor.getUnidades().stream().map(UnidadeModel::getEquipamentos).forEach((e) -> {
                        if (e.contains(equipamento)) {
                            ChamadoModel chamadoModel = new ChamadoModel();
                            chamadoModel.setDataAbertura(Instant.now());
                            chamadoModel.setDescricao("CHAMADO HORA");
                            chamadoModel.setEquipamento(equipamento);
                            chamadoModel.setGestor(gestor);
                            chamadoModel.setStatus(false);
                            chamadoService.saveChamado(chamadoModel);
                            this.cont = 1;
                        }
                    });
                    if (this.cont == 0) {
                        System.out.println(equipamento.getDescricao() + " não pertence a nenhuma de suas unidades.");
                    }
                }));
    }

    @Test
    @Order(11)
    public void alocarTecnicos() {
        chamadoService.findByCodigo("07bdf0c9-9dfd-4bfc-be67-90f046036b77").ifPresent((ch) -> tecnicoService.findByTecnicoByEmail("miguel@amplomed.com").ifPresent((tec) -> {
            ch.getTecnicos().add(tec);
            chamadoService.saveChamado(ch);
        }));
    }

    @Test
    @Order(12)
    public void findAll() {

        equipamentoService.findEquipamentosByOrderByNome().stream()
                .map(EquipamentoModel::getUnidade)
                .map(UnidadeModel::getNome).forEach(System.out::println);

    }

}
