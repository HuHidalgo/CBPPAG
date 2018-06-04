package serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.configuracion.PersistenceConfiguration;
import com.cenpro.sircie.configuracion.ServiceConfiguration;
import com.cenpro.sircie.model.seguridad.Usuario;
import com.cenpro.sircie.service.IUsuarioService;

@ContextConfiguration(classes = { ServiceConfiguration.class, PersistenceConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioServiceTest
{
    private @Autowired IUsuarioService usuarioService;
    
    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buscarPorIdUsuarioParaInicioSesionTest()
    {
        Usuario u = usuarioService.buscarPorIdUsuarioParaInicioSesion("hanz.llanto");
        System.out.println(u);
    }
}