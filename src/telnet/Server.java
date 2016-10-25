package telnet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private Method methods[];
	private Beispiel b;
	
	public Server(){
		
		this.b = new Beispiel();
		this.methods = b.getClass().getDeclaredMethods();
	}
	
	public String executeMethod(String methodName, String param[]) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			Method method = this.getMethod(methodName);
			
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
		        			pw.print(s.getMethods()[i].getName() + "(");
		            		
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
		        			pw.println("Syntax: invoke [Methodenname] [Parameter1] [Parameter2] usw.");
		        		}else{
		        			String parameter[] = new String[input.length-2];
		        			
		        			for(int i = 0; i < parameter.length; i++){
		        				parameter[i] = input[i+2];
		        			}
		        			
		        			pw.println(s.executeMethod(input[1], parameter));
		        		}
		        		break;
		        	case "exit":
		        		end = true;
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
