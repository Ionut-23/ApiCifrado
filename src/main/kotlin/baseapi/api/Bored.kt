package baseapi.api

import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Bored (var actividad: String, var precio : Int) {
    @Id
    @GeneratedValue
    private val id : Long? = null

    override fun equals(other: Any?): Boolean {
        if(other is Bored){
            return other.id == id && id != null
        }else{
            return false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(id, actividad, precio)
    }

    override fun toString(): String {
        return "$id para esta actividad de $actividad el precio es de $precio"
    }
}

/*
{
  "actividad": "Escribe una nota de agradecimiento a alguien",
  "type": "social",
  "participantes": 1,
  "precio": 0,
  "enlace": "",
  "clave": "1770521",
  "accesibilidad": 0
}
 */