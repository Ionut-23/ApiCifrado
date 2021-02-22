package baseapi.api

import java.util.*
import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@RestController
class BoredController (private val boredRepository : BoredRepository) {
    @GetMapping("/bored")
    fun getAllBoreds() : List<Bored> {
        return boredRepository.findAll()
    }
    @PostMapping("/bored")
    fun insertBored(@RequestBody bored : Bored){
        boredRepository.save(bored)
    }
    @GetMapping("/bored/{id}")
    fun getBored(@PathVariable id : Long) : Bored {
        val encriptar = id.toString()
        val llave = "MeAburro"
        val descifrado = descifrar(cifrar(encriptar,llave),llave)
        return boredRepository.findById(descifrado.toLong()).get()
    }

    @DeleteMapping("/bored/{id}")
    fun deleteBored(@PathVariable id : Long){
        boredRepository.deleteById(id)
    }
}

private fun cifrar(textoEnString : String, llaveEnString : String) : String{
    println("Voy a cifrar $textoEnString")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, getKey(llaveEnString))
    val textCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(textoEnString.toByteArray(Charsets.UTF_8)))
    println("He obtenido $textCifrado")
    return textCifrado
}
@Throws(BadPaddingException::class)
private fun descifrar(textoCifrrado : String, llaveEnString : String) : String {
    println("Voy a descifrar $textoCifrrado")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, getKey(llaveEnString));
    val textDescifrado = String(cipher.doFinal(Base64.getDecoder().decode(textoCifrrado)))
    println("He obtenido $textDescifrado")
    return textDescifrado
}
private fun getKey(llaveEnString : String): SecretKeySpec {
    var llaveUtf8 = llaveEnString.toByteArray(Charsets.UTF_8)
    val sha = MessageDigest.getInstance("SHA-1")
    llaveUtf8 = sha.digest(llaveUtf8)
    llaveUtf8 = llaveUtf8.copyOf(16)
    return SecretKeySpec(llaveUtf8, "AES")
}