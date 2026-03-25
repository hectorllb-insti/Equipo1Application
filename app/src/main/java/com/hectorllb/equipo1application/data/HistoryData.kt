package com.hectorllb.equipo1application.data

// Establecemos fuera de la lista de datos el tipo de dato que tendrá nuestra lista de etapas
data class EtapaHistoria(
        val titulo: String,
        val descripcion: String,
        val idImagen: Int
)

// Creamos un Objeto que nos ayudará a obtener la lista de todas las etapas en cualquier punto de la app
object HistoryData {
    val listaEtapas = listOf(
            EtapaHistoria(
                    titulo = "Prehistoria e Íberos (hasta el s. III a.C.)",
                    descripcion = "Los primeros pobladores de la península dejaron su huella en cuevas como Altamira. Posteriormente, los pueblos íberos y celtas dominaron el territorio antes de la llegada de los romanos.",
                    idImagen = android.R.drawable.ic_menu_mapmode
            ),
            EtapaHistoria(
                    titulo = "Hispania Romana (s. III a.C. – s. V d.C.)",
                    descripcion = "Los romanos iniciaron la conquista de Hispania en el siglo III a.C. tras las Guerras Púnicas. La romanización fue profunda: se construyeron calzadas, acueductos, teatros y ciudades como Emerita Augusta (Mérida) o Caesaraugusta (Zaragoza). El latín se impuso como lengua común, dando origen siglos después al castellano, el catalán y el gallego. Emperadores como Trajano y Adriano nacieron en Hispania.",
                    idImagen = android.R.drawable.ic_menu_compass
            ),
            EtapaHistoria(
                    titulo = "Visigodos y llegada del Islam (s. V – s. VIII)",
                    descripcion = "Tras la caída del Imperio Romano, los visigodos establecieron su reino con capital en Toledo, unificando gran parte de la península antes de la conquista musulmana en el 711.",
                    idImagen = android.R.drawable.ic_menu_sort_by_size
            ),
            EtapaHistoria(
                    titulo = "Al-Andalus y la Reconquista (s. VIII – 1492)",
                    descripcion = "Durante siglos, Al-Andalus fue uno de los centros culturales más avanzados de Europa. Córdoba, con su gran mezquita y su califato, albergó a filósofos, médicos y astrónomos. Mientras tanto, los reinos cristianos del norte fueron avanzando hacia el sur. La Reconquista culminó el 2 de enero de 1492 con la toma de Granada por los Reyes Católicos, Isabel I de Castilla y Fernando II de Aragón.",
                    idImagen = android.R.drawable.ic_menu_day
            ),
            EtapaHistoria(
                    titulo = "El Descubrimiento de América (1492)",
                    descripcion = "Bajo el reinado de los Reyes Católicos, la expedición de Cristóbal Colón llegó a América, iniciando una etapa de expansión global sin precedentes para España.",
                    idImagen = android.R.drawable.ic_menu_directions
            ),
            EtapaHistoria(
                    titulo = "El Siglo de Oro (s. XVI – XVII)",
                    descripcion = "Una época de máximo esplendor cultural, literario y artístico con figuras de la talla de Cervantes, Velázquez, Lope de Vega y Quevedo, coincidiendo con el cénit del Imperio Español.",
                    idImagen = android.R.drawable.star_on
            )
    )
}
