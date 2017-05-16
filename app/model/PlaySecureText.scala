package model

import play.api.libs.Crypto

/**
  * Created by vpandiyan001 on 5/16/2017.
  */
class PlaySecureText(text: String) {
  def encrypt: String = Crypto.encryptAES(text)
  def decrypt: String = Crypto.decryptAES(text)
}

//object PlaySecureText {
  //implicit def stringToString(s: String) = new PlaySecureText(s)
//}