package traitement;


	import java.io.FileOutputStream;
	import java.util.Date;

	import com.itextpdf.text.Anchor;
	import com.itextpdf.text.BadElementException;
	import com.itextpdf.text.BaseColor;
	import com.itextpdf.text.Chapter;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Element;
	import com.itextpdf.text.Font;
	import com.itextpdf.text.List;
	import com.itextpdf.text.ListItem;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.Phrase;
	import com.itextpdf.text.Section;
	import com.itextpdf.text.pdf.PdfPCell;
	import com.itextpdf.text.pdf.PdfPTable;
	import com.itextpdf.text.pdf.PdfWriter;

import beans.Client;
import beans.Location;
import beans.Vehicule;


	public class pdfContrat {
	    private static String FILE = "c://LocationVehicule/HelloWorld2.pdf";
	    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	    private static Font bfont = new Font(Font.FontFamily.TIMES_ROMAN, 24,
	            Font.BOLD);
	    private static Font caatFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
	            Font.BOLD);
	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL);
	    private static Font red= new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10,
	            Font.BOLD);
	    private static Font Bold = new Font(Font.FontFamily.TIMES_ROMAN, 14,
	            Font.BOLD);
	    public  void Contratpdf(Location l,Vehicule v,Client c,String type) {
	        try {String x="";
	        	if(l.isModePayement()) {
	        		x="liquide";
	        	}else {
	        		x="cheque";
	        	}
	        	
	        		String daa="";
	        		if(l.isHeure()) {
	        			daa=l.getDateDebut()+" à " + l.getHeureDebut() + " et "+ l.getHeureFin();
	        		}else {
	        			daa=l.getDateDebut() + " et " + l.getDateFin();
	        		}
	        	
	        	
	            Document document = new Document();
	            PdfWriter.getInstance(document, new FileOutputStream("c://LocationVehicule/Contrat/"+l.getIdLocation()+".pdf"));
	            document.open();
	            addMetaData(document);
	            Paragraph preface = new Paragraph();
	 	       
	            preface.add(new Paragraph(
		                " Agence De Location De Vehicule vProcompany "  , 
		                bfont));
	            addEmptyLine(preface, 1);
	            
		        preface.add(new Paragraph("Contrat De Location N° : " +l.getIdLocation(), catFont));

		        
		        preface.add(new Paragraph(
		                "Date :" + new Date(),
		                redFont));
		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph(
		                "condition géneral du contrat :",
		                subFont));
		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph(
		                "* Vérification du nom : Le nom du locataire doit être le même sur le contrat de location et sur son permis de conduire. Le propriétaire peut également exiger une facture portant le même nom que sur les deux documents précités.",
		                smallBold));
		        preface.add(new Paragraph(
		                "* le locataire doit être âgé d’au moins 18 ans et est titulaire d’un permis de conduire algerian en cour de validité",
		                smallBold));
		        preface.add(new Paragraph(
		                "* Etat des lieux d’entrée de location : Vérification de l’état du véhicule (dommages, fonctionnement). Cet état des lieux d’entrée de location doit être signé des deux parties.\r\n" , 
		                  smallBold));
		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph(
		                "Condition supplémentaires",
		                Bold));
		        
		        preface.add(new Paragraph(
		                "* Non-conformité du véhicule réservé\r\n" + 
		                "- En cas de non-conformité du véhicule réservé par rapport à la description, le locataire peut annuler la réservation en contactant le service client de vPro par mail à companyvpro@gmail.com .",
		                smallBold));
		        preface.add(new Paragraph(
		                "* En cas d’accident, vol ou panne :\r\n" + 
		                "		        	- le locataire doit prévenir le propriétaire, établir un constat amiable en cas d’accident, un dépôt de plainte au commissariat en cas de vol.\r\n" + 
		                "		        	-Prévenez dès que possible vPro au 06 72 39 31 38\r\n" + 
		                "		        	-ou par mail companyvpro@gmail.com",
		                smallBold));
		        preface.add(new Paragraph(
		        		"* D’autres conducteurs sont autorisés à conduire le véhicule sous réserve de respecter les conditions d’âge et d’ancienneté du permis de conduire en fonction de la valeur du véhicule. Listez ces conducteurs au verso du contrat en notant leur n° de permis..",
		                smallBold));
		        
		        
		        addEmptyLine(preface, 1);
		       
		        preface.add(new Paragraph(
		                "Information du client  : ",
		                Bold));
		        preface.add(new Paragraph(
		               "Numero de permis de conduire N° " +c.getIdClient(),
		                redFont));
		        preface.add(new Paragraph(
		                "Nom Prenom et Date De Naissance : " + c.getNom()+" "+c.getPrenom() + " né le "+c.getDateNaissance(),
		                redFont));
		        preface.add(new Paragraph(
		               "Numero de telephone :" +c.getTelephone(),
		                redFont));
		        preface.add(new Paragraph(
		                "modalité de payment : " +x,
		                redFont));
		        preface.add(new Paragraph(
		                "Description du vehicule : ",
		                Bold));
		        preface.add(new Paragraph(
		                "matricule du vehicule : "+v.getMatricule(),
		                redFont));
		       
		        preface.add(new Paragraph(
		                " vehicule de type : " +type+" Marque  : "+v.getMarque()+"  Modele : "+v.getModele(),
		                redFont));
		        preface.add(new Paragraph(
		                "Periode de Location : ",
		                Bold));
		        preface.add(new Paragraph(
		                " peridode entre le : " +daa,
		                redFont));

		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph(
		                " signature  " ,
		                Bold));
		        addEmptyLine(preface, 2);
		        preface.add(new Paragraph(
		                " * ceci est votre contrat de location vous devez en disposez lors de la remise du vehicule *",
		                redFont));

		        document.add(preface);
		        // Start a new page
		        document.newPage();	            
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	  
	    private static void addMetaData(Document document) {
	        document.addTitle("Contract");
	        document.addSubject("location Vehicule");
	        document.addKeywords("matricule, contrat, vPro");
	        document.addAuthor("vPro");
	        document.addCreator("vPro");
	    }

	 
	

	  

	 

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	
}
