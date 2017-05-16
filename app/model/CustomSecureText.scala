package model

import aes.CustomAESCrypto

/**
  * Created by vpandiyan001 on 5/16/2017.
  */
class CustomSecureText(text: String) {
  def encrypt(privateKey: String): String = CustomAESCrypto.encrypt(text, privateKey)
  def decrypt(privateKey: String): String = CustomAESCrypto.decrypt(text, privateKey)
}
