package baseapi.api

import org.springframework.web.bind.annotation.*

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
        return boredRepository.findById(id).get()
    }

    @DeleteMapping("/bored/{id}")
    fun deleteBored(@PathVariable id : Long){
        boredRepository.deleteById(id)
    }
}