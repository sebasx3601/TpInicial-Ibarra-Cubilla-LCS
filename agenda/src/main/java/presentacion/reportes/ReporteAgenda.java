package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import dto.PersonaDTO;

import persistencia.conexion.Conexion;

public class ReporteAgenda
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteAgenda.class);
	//Recibe la lista de personas para armar el reporte
    public ReporteAgenda(List<PersonaDTO> personas)
    {
    	//Hardcodeado
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));		
    	try		{																					//ReporteAgenda.jasper
			
    		File n = new File("");
    		String dir = n.getAbsolutePath()+"\\reportes\\Reporte.jrxml";
    		this.reporte = JasperCompileManager.compileReport(dir);
    		//this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes" + File.separator + "Reporte.jasper" );
			//this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
			//		new JRBeanCollectionDataSource(personas));
            this.reporteLleno = JasperFillManager.fillReport(this.reporte,parametersMap,Conexion.getConexion().getSQLConexion());
            
            /* Intento de poder trasladar el report a una carpeta source foulder intento fallido
            File n = new File("");
    		//this.reporte = (JasperReport) JRLoader.loadObject(this.getClass().getResource(n.getAbsolutePath()+"\\src\\main\\resources\\reporte\\Reporte.jasper")); // Como se suponia que queria
    		String dir = n.getAbsolutePath()+"\\reporte\\Reporte.jrxml";	//Como aparentemente funciona
    		//\\src\\main\\resources
    		this.reporte = JasperCompileManager.compileReport(dir);
    		
            this.reporteLleno = JasperFillManager.fillReport(this.reporte,parametersMap,Conexion.getConexion().getSQLConexion());
             */
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	