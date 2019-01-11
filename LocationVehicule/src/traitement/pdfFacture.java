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
import beans.Facture;
import beans.Location;
import beans.Vehicule;


	public class pdfFacture {
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

	    public void Facturepdf(Facture f,Vehicule v,Client c,float montanttotal,String type,Location l) {
	        try {String x="";
	        	if(l.isModePayement()) {
	        		x="liquide";
	        	}else {
	        		x="cheque";
	        	}
	        	String p="";
	        	if(f.isFactureDePenalite()) {
	        		p=" de pénalité ";
	        	}else {
	        		p=" ";
	        	}
	        	float frais=montanttotal - f.getMontant();
	            Document document = new Document();
	            PdfWriter.getInstance(document, new FileOutputStream("c://LocationVehicule/Facture/"+f.getCodeFacture()+".pdf"));
	            document.open();
	            addMetaData(document);
	            addTitlePage(document);
	            
	            
	            Paragraph preface = new Paragraph();
	 	       
		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph(
		                " Agence De Location De Vehicule vProcompany "  , 
		                bfont));
	            addEmptyLine(preface, 1);
	            
		        preface.add(new Paragraph("Facture N° : " + f.getCodeFacture(), catFont));

		        
		        preface.add(new Paragraph(
		                "Date :" + new Date(),
		                redFont));
		        addEmptyLine(preface, 1);
		       
		        preface.add(new Paragraph(
		                "à : "+c.getNom()+" "+c.getPrenom(),
		                Bold));
		        preface.add(new Paragraph(
			               "Numero de telephone :" +c.getTelephone(),
			               Bold));
		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph(
		                "modalité de payment : "+x,
		                Bold));
		        preface.add(new Paragraph(
		                "facture " + p+" concerne la location N° :" +l.getIdLocation(),
		                Bold));
		        addEmptyLine(preface, 1);

		        preface.add(new Paragraph(
		                "Description du vehicule : ",
		                subFont));
		        preface.add(new Paragraph(
		                "matricule du vehicule : "+v.getMatricule(),
		                Bold));
		       
		        preface.add(new Paragraph(
		                " vehicule de type : " + type+ " Marque  : " +v.getMarque()+ "  Modele : "+v.getModele(),
		                Bold));
		        
		        addEmptyLine(preface, 2);
		        preface.add(new Paragraph(
		                "Montant Brut : " +f.getMontant() + " DA",
		                Bold));
		        preface.add(new Paragraph(
		                "frais de l'agence  : "+frais + " DA",
		                Bold));
		        preface.add(new Paragraph(
		                "Montant Total de la facture a payé : " +montanttotal + " DA",
		                subFont));
		        
		        addEmptyLine(preface, 6);

		        preface.add(new Paragraph(
		                " signature  " ,
		                Bold));
		        
		        addEmptyLine(preface, 8);

		        

		        document.add(preface);
		        // Start a new page
		        document.newPage();
	            
	            
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	  
	    private static void addMetaData(Document document) {
	        document.addTitle("Facture");
	        document.addSubject("location Vehicule");
	        document.addKeywords("Facture, contrat, vPro");
	        document.addAuthor("vPro");
	        document.addCreator("vPro");
	    }

	    private static void addTitlePage(Document document)
	            throws DocumentException {
	       
	    }

	

	  

	 

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	
}
