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

import beans.AvisFinLocation;
import beans.Client;
import beans.Vehicule;


	public class pdfAvis {
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

	    public void Avispdf(AvisFinLocation A,String m,Client c) {
	        try {
	            Document document = new Document();
	            PdfWriter.getInstance(document, new FileOutputStream("c://LocationVehicule/Avis/"+A.getIdAvis()+".pdf"));
	            document.open();
	            addMetaData(document);
	            Paragraph preface = new Paragraph();
	 	       
	            addEmptyLine(preface, 1);
		        preface.add(new Paragraph(
		                " Agence De Location De Vehicule vProcompany "  , 
		                bfont));
	            addEmptyLine(preface, 1);
	            
		        preface.add(new Paragraph("Avis Fin De Location N° : " + A.getIdAvis(), catFont));

		        
		        preface.add(new Paragraph(
		                "Date :" + new Date(),
		                redFont));
		        addEmptyLine(preface, 1);
		       
		        preface.add(new Paragraph(
		                "Client : "+c.getNom()+" "+c.getPrenom(),
		                Bold));
		        preface.add(new Paragraph(
			               "Numero de telephone :" +c.getTelephone(),
			               Bold));
		        preface.add(new Paragraph(
			               "Numero de permis de conduire N° " +c.getIdClient(),
			                redFont));
		        addEmptyLine(preface, 1);
		       
		        preface.add(new Paragraph(
		                "Location N° " + A.getIdLocation(),
		                Bold));
		        addEmptyLine(preface, 1);

		        preface.add(new Paragraph(
		                "Condition du Retour de Vehicule : ",
		                catFont));
		        preface.add(new Paragraph(
		                "Date De Retour : "+A.getDateRetour(),
		                Bold));
		       
		        preface.add(new Paragraph(
		                " Heure De Retour: " +A.getHeureRetour(),
		                Bold));
		        
		        addEmptyLine(preface, 2);
		        preface.add(new Paragraph(
		                "Etat De Retour : "+A.getEtatRetour() +" %",
		                Bold));
		        preface.add(new Paragraph(
		                "Autres Description : "+A.getDescription(),
		                Bold));
		        addEmptyLine(preface, 3);
		        preface.add(new Paragraph(
		                " l'etat de retour de vehicule doit etre signé est approuvé par un employé,en cas d'infraction des lois ou des termes du contrat le client sera pénalisé ",
		                subFont));
		        
		        addEmptyLine(preface, 6);

		        preface.add(new Paragraph(
		                " signature  " ,
		                Bold));
		        
		        

		        document.add(preface);
		        // Start a new page
		        document.newPage();
	            
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	  
	    private static void addMetaData(Document document) {
	        document.addTitle("Avis Fin Location");
	        document.addSubject("location Vehicule");
	        document.addKeywords("matricule, Avis, vPro");
	        document.addAuthor("vPro");
	        document.addCreator("vPro");
	    }

	

	

	  

	 

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	
}
