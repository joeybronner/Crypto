package caesar;

import java.io.FileReader;

public class Caesar {
	
	
	public static void main(String[] args){
		Caesar x = new Caesar("files/caesar.txt");
		x.crack();
	}
	

	private char[] list = new char[]{
			'A','B','C','D','E','F','G',
			'H','I','J','K','L','M','N',
			'O','P','Q','R','S','T','U',
			'V','W','X','Y','Z'
	};

	private int[] cryp;

	public Caesar(String file){

		try {
			FileReader x = new FileReader(file);

			String holder = "";

			int ch;
			while((ch = x.read()) != -1)
				holder += Character.toString((char) ch) ;

			cryp = new int[holder.length() - 1];

			for(int p = 0; p < holder.length() - 1; ++p)
				cryp[p] = (int) holder.charAt(p);

			x.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crack(){
		try{
			for(int x = 0; x < list.length; ++x){
				String temp = "";
				for(int y = 0; y < cryp.length; ++y){
					int n = (cryp[y] + x);
					if(n > 65 && n < 90) {
						n = ((n + 1) - 65) % 25;
					} else {
						n = (n + 65) % 25;
					}
					temp += Character.toString(list[n]);
				}
				System.out.println(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void crack(int key){
		try{
			String temp = "";
			for(int y = 0; y < cryp.length; ++y){
				int n = (cryp[y] + (-key));
				if(n > 65 && n < 90) {
					n = (n - 65);
				} else {
					n = (n + 26);
				}
				temp += Character.toString(list[n]);
			}
			System.out.println(temp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}