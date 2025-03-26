import java.sql.*


data class rep_legal(
    val rep_id: Int,
    val rep_nombre: String,
    val rep_nombreusuario: String,
    val rep_direccion: String,
    val rep_telefono: String,
    val rep_contrasena: String
)

class ConexionBD {

    private val url = "jdbc:mysql://localhost:3306/CMC"
    private val user = "root"
    private val password = ""

    val rep_legal = mutableListOf<rep_legal>()

    fun ejecutarConexion() {
        try {
            val conn: Connection = DriverManager.getConnection(url, user, password)
            val stmt: Statement = conn.createStatement()
            val rs: ResultSet = stmt.executeQuery("SELECT * FROM rep_legal")

            while (rs.next()) {
                val rep_id = rs.getInt("rep_id")
                val rep_nombre = rs.getString("rep_nombre")
                val rep_nombreusuario = rs.getString("rep_nombreusuario")
                val rep_direccion = rs.getString("rep_direccion")
                val rep_telefono = rs.getString("rep_telefono")
                val rep_contrasena = rs.getString("rep_contrasena")

                val replegal =
                    rep_legal(rep_id, rep_nombre, rep_nombreusuario, rep_direccion, rep_telefono, rep_contrasena)
                rep_legal.add(replegal)
            }

            println("Conexión exitosa y datos cargados:")
            rep_legal.forEach { println(it) }

            conn.close()
        } catch (e: SQLException) {
            println("Error en la conexión:")
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            println("Error: El campo estado tiene un valor inválido en la base de datos.")
        }
    }
}

