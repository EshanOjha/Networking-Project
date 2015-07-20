import java.net.*;
import java.io.*;
class ChotaClient
{
public static void main(String data[])
{
try
{
Console console=System.console();
String rollNumber=console.readLine("enter roll number:");
String name=console.readLine("Enter Name:");
String sex=console.readLine("Enter Sex");
String request=rollNumber+","+name+","+sex+"\n";
Socket ck;
ck=new Socket("localhost",3055);
OutputStream os;
os=ck.getOutputStream();
OutputStreamWriter osw;
osw=new OutputStreamWriter(os);
osw.write(request);
osw.flush();
System.out.println("request sent");
InputStream is; 
is=ck.getInputStream();
InputStreamReader isr=new InputStreamReader(is);
StringBuffer sb=new StringBuffer();
int x;
while(true)
{
x=isr.read();
if(x=='\n')
{
break;
}
sb.append((char)x);
}
String response=sb.toString();
System.out.println("Received Response"+response);
ck.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}