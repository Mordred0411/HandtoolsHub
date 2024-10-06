package com.example.handtools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.handtools.ui.theme.HandtoolsTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HandtoolsTheme {
                val navController = rememberNavController()
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription= "Fondo de pantalla",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    NavigationGraph(navHostController = navController)
                }
            }
        }
    }
}

@Composable
fun MenuPrincipal(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Fondo de pantalla",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Column para los elementos del menu
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(125.dp))

            Text("Handtools",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

            // Botones del menu principal
            BotonMenu("Crear", onClick = { navController.navigate("crear") })
            BotonMenu("Ajustes", onClick = { navController.navigate("ajustes") })
            BotonMenu("Acerca de", onClick = { navController.navigate("acerca_de")})
            BotonMenu("Apoyar al desarrollador", onClick = { navController.navigate("apoyar") })
        }
    }
}

// Boton reutilizable
@Composable
fun BotonMenu(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = texto, modifier = Modifier, Color.White, fontSize = 18.sp)
    }
}

@Composable
fun PantallaMenuCrear(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Selecciona que deseas crear", style = MaterialTheme.typography.headlineMedium)

        BotonMenu("Notas", onClick = { navController.navigate("notas") })
        BotonMenu("Cuenta Bultos", onClick = { navController.navigate("cuenta_bultos") })
        BotonMenu("Cuenta Bultos con Anotaciones", onClick = { navController.navigate("cuenta_bultos_anotaciones") })
    }
}

@Composable
fun PantallaAjustes() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ajustes de la aplicacion", style = MaterialTheme.typography.headlineMedium)
    }
}


@Composable
fun PantallaAcercaDe() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Acerca de la aplicacion", style = MaterialTheme.typography.headlineMedium)

        Text(
            text = "Esta aplicación fue creada para ayudar a organizar tareas y contadores personalizados." +
                    " Con el fin de ayudar a mantener un registro ordenado dentro de tareas basicas en la organizacion de, por ejemplo, un almacen o inventario.",
            modifier = Modifier.padding(top = 16.dp)
        )

        // Añadimos informacion sobre mi
        Text(text = "Desarrollador: Tu Nombre", modifier = Modifier.padding(top = 8.dp))
        Text(text = "Contacto: tunombre@example.com", modifier = Modifier.padding(top = 4.dp))
    }
}

/*Recomendaciones para la sección "Acerca de":
Objetivo de la app: Explicar qué problema resuelve o cómo facilita la vida del usuario.
Historia de la app: Un pequeño párrafo explicando por qué decidiste crear la aplicación.
Futuras características: Hablar de posibles actualizaciones que planeas agregar.
Redes sociales y contacto: Proporcionar links a redes sociales o correos donde los usuarios puedan contactarte.*/
@Composable
fun PantallaApoyar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Apoyar al desarrollador", style = MaterialTheme.typography.headlineMedium)
        Text("Si deseas apoyar el desarrollo de esta aplicacion, puedes hacerlo a traves de la siguiente pagina: ")

        Button(
            onClick = { /* Navegar a la pagina de donaciones */},
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Donar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPrincipalPreview() {
    HandtoolsTheme {
        val navController = rememberNavController()
        MenuPrincipal(navController)
    }
}