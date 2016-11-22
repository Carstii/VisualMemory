package telnet;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;

import memory.Memory;

public class Server {

	private Method methods[];
	private Memory b;
	
	public Server(){
		
		this.b = new Memory(12);
		this.methods = b.getClass().getDeclaredMethods();
	}
	
	public String executeMethod(int methodNumber, String param[]) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			Method method = this.getMethod(methodNumber);
			
			if(method != null){
				int countParameter = method.getParameterCount();
				if(countParameter == param.length){
					Object parameter[] = new Object[countParameter];
					
					for(int i = 0; i < method.getParameterCount(); i++){
						
						if(method.getParameterTypes()[i].equals(Integer.TYPE)){
							parameter[i] = Integer.parseInt(param[i]);
						}else if(method.getParameterTypes()[i].equals(Double.TYPE)){
							parameter[i] = Double.parseDouble(param[i]);
						}else if(method.getParameterTypes()[i].equals(Boolean.TYPE)){
							parameter[i] = Boolean.parseBoolean(param[i]);
						}else{
							parameter[i] = param[i];
						}
					}
					
					method.invoke(this.b, parameter);
					return "Methode " + method.getName() + " wurde erfolgreich ausgefuehrt";
				}else{
					return "Die Anzahl der uebergebenen Parameter ist falsch, es werden: " + countParameter + " Parameter erwartet.";
				}
			}else{
				return "Methode nicht gefunden!";
			}
	}
	
	public Method getMethod(String name){
		for(int i = 0; i < this.methods.length; i++){
			if(methods[i].getName().equals(name)){
				return methods[i];
			}
		}
		
		return null;
	}
	
	public Method getMethod(int number){
		return methods[number];
	}
	
	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

	public static void main(String[] args){
		
		try{
			
			Server s = new Server();
			
            
			
			boolean end = false;
	        
			ServerSocket so = new ServerSocket(23);
			
			Socket socket = so.accept();
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			pw.println("Sie sind jetzt mit dem Server verbunden!");
			
			
			while(!end){
		        String eingabe = br.readLine();
		        
		        String input[] = eingabe.split(" ");
		        
		        for(int i = 0; i < input.length; i++){
		        	System.out.print(input[i] + " ");
		        }
		        
		        System.out.println();
		        
		        switch(input[0]){
		        	case "help":
		        		
		        		break;
		        	case "methods":
		        		for(int i = 0; i < s.getMethods().length; i++){
		        			pw.print((i+1) + " " + s.getMethods()[i].getName() + "(");
		            		
		            		Parameter parameter[] = s.getMethods()[i].getParameters();
		            		
		            		for(int a = 0; a < parameter.length; a++){
		            			pw.print(parameter[a].toString());
		            			if( a < parameter.length-1){
		            				pw.print(", ");
		            			}
		            		}
		            		pw.println(")");
		            	}
		        		break;
		        	case "invoke" :
		        		if(input.length < 2){
		        			pw.println("Syntax: invoke [Methodennummer] [Parameter1] [Parameter2] usw.");
		        		}else{
		        			String parameter[] = new String[input.length-2];
		        			
		        			for(int i = 0; i < parameter.length; i++){
		        				parameter[i] = input[i+2];
		        			}
		        			try{
		        				pw.println(s.executeMethod(Integer.parseInt(input[1]) - 1 , parameter));
		        			}catch(NumberFormatException ex){
		        				pw.println("Syntax: invoke [Methodennummer] [Parameter1] [Parameter2] usw.");
		        			}
		        		}
		        		break;
		        	case "exit":
		        		end = true;
		        		break;
		        	case "file":
		        		File file = new File("Empfangen.txt");
		                OutputStream outputStream = new FileOutputStream(file);
		                InputStream inputStream = socket.getInputStream();

		                byte[] buffer = new byte[16384];
		                int len = 0;
		                while ((len = inputStream.read(buffer)) > 0) {
		                    outputStream.write(buffer, 0, len);
		                }
		                outputStream.close();
		                inputStream.close();
		                System.out.println("Es wurde eine Datei empfangen");
		        		break;
		        	default:
		        		pw.println("Dieser Befehl existiert nicht");
		        		
		        }
		        
	        }
			
			so.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
