package Utils;

import java.security.MessageDigest;
import java.util.HexFormat;

public class HashSecurity {
	/**
	 * Recebe uma senha em texto puro e devolve a senha em Hash SHA-256.
	 * @param senha
	 * @return
	 */
	public static String senhaCriptografada(String senha) {
		try {
			//Trasnformando a informação recebida (senha) em Hash
			MessageDigest codigo = MessageDigest.getInstance("SHA-256");
			byte[] hashByts = codigo.digest(senha.getBytes());
			return HexFormat.of().formatHex(hashByts);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar hash da senha: "+e.getMessage());
		}
	}

}
