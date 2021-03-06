package Java_Training.practice100.no213.no059;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

public class No059 {
	public static void main(String[] args) {
		int errorCode = 0;
		if(args.length < 1) {
			System.err.println("引数にURLを指定し再実行してください");
			System.exit(1);
		}
		try{
			final URL url = new URL(args[0]);
			HttpURLConnection connect = (HttpURLConnection)url.openConnection();
			connect.setRequestMethod("GET");
			connect.setInstanceFollowRedirects(false);
			final int responseCode = connect.getResponseCode();
			System.out.println("/***レスポンスコード***/\n" + responseCode + "\n");

			makeHeaderFile(connect);
			System.out.println("/***レスポンスヘッダを以下ファイルに出力しました***/");
			System.out.println("header.txt\n");

			makeBodyFile(connect);
			System.out.println("/***レスポンスボディを以下ファイルに出力しました***/");
			System.out.println("body.txt");
		} catch(MalformedURLException e) {
			errorCode = 1;
			e.printStackTrace();
		} catch(UnknownHostException e) {
			errorCode = 2;
			e.printStackTrace();
		} catch(IOException e) {
			errorCode = 3;
			e.printStackTrace();
		} finally {
			System.exit(errorCode);
		}
	}
		public final static void makeHeaderFile(HttpURLConnection connectHeader) {
			try(final BufferedWriter output = new BufferedWriter(new FileWriter("Header.txt"))) {
				final Map<?,?> headers = connectHeader.getHeaderFields();
				final Iterator<?> header = headers.keySet().iterator();
				while(header.hasNext()) {
					final String headerKey = (String)header.next();
					output.write(" " + headerKey + ":" + headers.get(headerKey));
					output.newLine();
				}
				System.out.println(headers.get("Content-Length"));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		public final static void makeBodyFile(HttpURLConnection connectBody) {
			try(final BufferedInputStream input = new BufferedInputStream(connectBody.getInputStream());
				final BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("body.txt"))) {
					int byteLength;
					byte[] maxbyte = new byte[1024];
					while((byteLength = input.read(maxbyte)) != -1) {
						output.write(maxbyte, 0, byteLength);
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}