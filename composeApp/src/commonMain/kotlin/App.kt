import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bottombar.BottomBarScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.ScaleTransition
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import helloworld.composeapp.generated.resources.Res
import helloworld.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen()){navigator: Navigator ->
            SlideTransition(navigator)
            //FadeTransition(navigator)
            //ScaleTransition(navigator)
        }
    }
}

class MainScreen:Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content(){
        var navigator = LocalNavigator.currentOrThrow
        var name: String by remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = name,
                onValueChange = {name = it}
            )
            Spacer(modifier = Modifier.height(20.dp))
            //if(name.isNotEmpty()){
            AnimatedVisibility(name.isNotEmpty()){
                if(name == "macia"){
                    Text("VAMOS !!", fontSize = 25.sp, fontWeight = FontWeight.Bold )
                }else {
                    Text("Hola $name", fontSize = 25.sp)
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                navigator.push(SecondScreen())
            }){
                Text( "Pasamos a la segunda pantalla")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                navigator.push(BottomBarScreen())
            }){
                Text( "Bottom Bar")
            }
        }
    }
}


class SecondScreen:Screen{
    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Text("Segunda Pantalla", fontSize = 26.sp, color = Color.Blue )
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                //navigator.push(MainScreen())
                navigator.pop()
            }){
                Text( "Volvemos a la pantalla principal")
            }
        }

    }
}