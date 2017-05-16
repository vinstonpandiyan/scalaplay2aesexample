package controllers

import model.CustomSecureText
import play.api.mvc._

/**
  * It uses the built in Java Cryptographic utilities
  * to encrypt and decrypt the text with the private key provided.
  * Usage: expose the implicit method to easy use;
  * <li>implicit def stringToString(text: String) = new SecureText(text)</li>
  * <li>"TEXT_CONTENT".encrypt(privateKey)</li>
  * <li>"[Encrypted_Value]".decrypt(privateKey)</li>
  *
  * Created by vpandiyan001 on 5/16/2017.
  */
object CustomAES extends Controller {

  implicit def stringToString(s: String) = new CustomSecureText(s)

  final val privateKey: String = "5;KbUKb?xNsoJ<=]f</s<NRdulunzS<EVBDp[;:vrhA<G^yECCC/NkVB3GjA^p@r"

  def encrypt(text: String) = Action {
    val encryptText = text.encrypt(privateKey)
    Ok(s"Encrypted Text: $encryptText")
  }

  def decrypt(text: String) = Action {
    val decryptText = text.decrypt(privateKey)
    Ok(s"Decrypted Text: $decryptText")
  }

}
