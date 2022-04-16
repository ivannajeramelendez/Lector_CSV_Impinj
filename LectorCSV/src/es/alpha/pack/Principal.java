package es.alpha.pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Principal {

	  public static final String SEPARATOR=",";
	   public static final String QUOTE="\"";
	public static void main(String[] args) {

			  BufferedReader br = null;
			  
			  try {
			     
			     br =new BufferedReader(new FileReader(args[0]));
			     String line = br.readLine();
			     line =br.readLine();
			     line =br.readLine();
			     line =br.readLine();
			     line =br.readLine();
			     line =br.readLine();
			     List<Participante> participantes=new ArrayList<Participante>();
			     participantes.add(new Participante());
			     Timestamp aux1 = null;
			     while (null!=line) {
			        String [] fields = line.split(SEPARATOR);
			        fields = removeTrailingQuotes(fields);
			        String subcadena=fields[0].substring(2);
			        try {
			        	long conversion=(short)Long.parseLong(subcadena,16);
				        int numero = (int) conversion;
				        int longitud=fields[1].length();
				        while(longitud<13) {
				        	fields[1]=fields[1]+"0";
				        	longitud=fields[1].length();
				        }
				        Timestamp date=new Timestamp(Long.parseLong(fields[1]));
				        
				        short tem=(short)numero;
				        Participante ultimoParticipante=participantes.get(participantes.size()-1);
				        if(ultimoParticipante.getId()!=tem) {
			        		ultimoParticipante=participantes.get(participantes.size()-1);
			        		ultimoParticipante.setFin(aux1);
				        	Participante participanteNuevo=new Participante();
				        	participanteNuevo.setId(tem);
				        	participanteNuevo.setInicio(date);
				        	participantes.add(participanteNuevo);
				        }

				        aux1=date;
			        }catch(NumberFormatException e) {
			        	
			        }

			        line = br.readLine();
			     }
			     Participante ultimoParticipante=participantes.get(participantes.size()-1);
			     ultimoParticipante.setFin(aux1);
			     participantes.remove(0);
			     br.close();

			    		FileWriter csvWriter = new FileWriter(args[0].split("\\.")[0]+"decode.csv");
			    		csvWriter.append("Id");
			    		csvWriter.append(",");
			    		csvWriter.append("Inicio");
			    		csvWriter.append(",");
			    		csvWriter.append("Fin");
			    		csvWriter.append(",");
			    		csvWriter.append("Tiempo");
			    		csvWriter.append(",");
			    		csvWriter.append("Nombre");
			    		csvWriter.append("\n");

			    		for (Participante participante : participantes) {
			    		    csvWriter.append( String.valueOf(participante.getId())+",");
			    		    csvWriter.append( participante.getInicio()+",");
			    		    csvWriter.append(participante.getFin()+",");
			    		    long tiempo=participante.getFin().getTime()-participante.getInicio().getTime();
			    		    int minutos=(int)tiempo/60000;
			    		    int segundos=(int)(tiempo%60000)/1000;
			    		    int milisegundos=(int)(tiempo%600000)/1000;
			    		    csvWriter.append(minutos+" min. y "+segundos+" seg. y "+milisegundos+" miliseg."+",");
			    		    csvWriter.append("\n");
			    		}

			    		csvWriter.flush();
			    		csvWriter.close();
			  } catch (Exception e) {
				  e.printStackTrace();
			  } finally {
			     if (null!=br) {
			        try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
			  }
		}
		   private static String[] removeTrailingQuotes(String[] fields) {

			      String result[] = new String[fields.length];

			      for (int i=0;i<result.length;i++){
			         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "").replaceAll("\\.", "");
			      }
			      return result;
			   }
}
