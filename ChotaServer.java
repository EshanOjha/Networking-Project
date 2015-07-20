import java.net.*;
import java.io.*;
class ChotaServer
{
private ServerSocket ss;
private Socket ck;
ChotaServer()
{
try
{
ss=new ServerSocket(3055);
startListening();
}catch(Exception e)
{
System.out.println("3055 port not available");
}
}
public void startListening()
{
try
{
while(true)
{
System.out.println("server is ready");
ck=ss.accept();
System.out.println("request is arrived");
new RequestProcessor(ck);
}
}catch(Exception e)
{
System.out.println(e);
}
}
public static void main(String data[])
{
ChotaServer sc=new ChotaServer();
}
}
class RequestProcessor extends Thread
{
private Socket ck;
RequestProcessor(Socket ck)
{
this.ck=ck;
start();
}
public void run()
{
try
{
InputStream is;
is=ck.getInputStream();
InputStreamReader isr;
isr=new InputStreamReader(is);
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
String request=sb.toString();
System.out.println("Request String:"+request);
int c1,c2;
String pc1,pc2,pc3;
c1=request.indexOf(",");
c2=request.indexOf(",",c1+1);
pc1=request.substring(0,c1);
pc2=request.substring(c1+1,c2);
pc3=request.substring(c2+1);
int roll=Integer.parseInt(pc1);
String name=pc2;
String sex=pc3;
System.out.println("Roll No Received"+roll);
System.out.println("Name Received"+name);
System.out.println("Sex Received"+sex);
String response="DataSaved";
OutputStream os;
os=ck.getOutputStream();
OutputStreamWriter osw;
osw=new OutputStreamWriter(os);
osw.write(response);
osw.flush();
ck.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}


