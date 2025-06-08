import dados.modelo.EntradaDiario;
import negocio.DiarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TesteFiltros {
    private DiarioService service;


    @BeforeEach
    public void setUp()throws ParseException{
        service = new DiarioService();
        //Aqui, antes de cada teste o repositorio será limpo
        service.limparEntradas();

        service.inserirEntrada("Estudar Java","15/06/2025","estudos");
        service.inserirEntrada("Fazer trabalho","14/06/2025","estudos");
        service.inserirEntrada("Limpar o quarto","12/06/2025","limpeza");
        service.inserirEntrada("Caminhada","12/06/2025","saúde");
    }

    //teste com apenas um filtro
    @Test
    public void testeFiltroTexto() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas("Estudar",null,null,null);
        assertEquals(1, res.size());
        assertTrue(res.get(0).getTexto().contains("Estudar"));
    }

    @Test
    public void testeFiltroData() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas(null,"14/06/2025","15/06/2025",null);
        assertEquals(2, res.size());
    }

    @Test
    public void testeFiltroCategoria() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas(null,null,null,"saúde");
        assertEquals(1, res.size());
    }

    //testes com dois filtros informados
    @Test
    public void testeFiltroTextoEData() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas("Estudar","15/06/2025","15/06/2025",null);
        assertEquals(1, res.size());
    }

    @Test
    public void testeFiltroTextoECategoria() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas("Caminhada",null,null,"saúde");
        assertEquals(1, res.size());
    }


    @Test
    public void testeFiltroDataECategoria() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas(null,"12/06/2025","12/06/2025","limpeza");
        assertEquals(1, res.size());
    }

    //teste para testar todos os filtros selecionados
    @Test
    public void testeTodos() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas("Caminhada","12/06/2025","12/06/2025","saúde");
        assertEquals(1, res.size());
        assertEquals("Caminhada", res.get(0).getTexto());
    }

    @Test
    public void testeSemFiltro() throws ParseException{
        List<EntradaDiario> res = service.filtrarentradas(null, null,null,null);
        assertEquals(4, res.size());
    }
}
