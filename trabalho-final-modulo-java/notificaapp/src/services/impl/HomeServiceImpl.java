package services.impl;

import models.Denuncia;
import services.interfaces.HomeService;

import java.util.List;

public class HomeServiceImpl implements HomeService {
    @Override
    public void feed(){
        DenunciaServicesImpl denunciaServices = new DenunciaServicesImpl();
        List<Denuncia> denuncias = denunciaServices.obterTodos();
        System.out.print("\n");
        for(Denuncia d: denuncias)
            System.out.printf("""
                        ╔═════════════════════════════════════════════════════════════════════════════╗
                        ║ id: %s
                        ║ titulo: %s
                        ║ descrição: %s
                        ║ status: %s
                        ╚═════════════════════════════════════════════════════════════════════════════╝
                        %n""", d.getIdDenuncia(), d.getTitulo(), d.getDescricao(), d.getStatusDenuncia());
    }

}
