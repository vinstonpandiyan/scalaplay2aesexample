package controllers

import model.PlaySecureText
import play.api.mvc._

/**
  * Created by vpandiyan001 on 5/16/2017.
  */
object PlayAES extends Controller {

  implicit def stringToString(s: String) = new PlaySecureText(s)

  def encrypt(text: String) = Action {
    val encryptText = text.encrypt
    Ok(s"Encrypted Text: $encryptText")
  }

  def decrypt(text: String) = Action {
    val decryptText = text.decrypt
    Ok(s"Decrypted Text: $decryptText")
  }

}
