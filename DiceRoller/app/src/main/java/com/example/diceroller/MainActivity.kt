package com.example.diceroller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                DiceRollerApp()

            }
        }
    }
}


@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }//armazena o núimero atual do dado
    var escolha by remember { mutableStateOf("") }//armazena o número digitado pelo usuário
    var resultado by remember { mutableStateOf("") }//resultado da verificação
    val context = LocalContext.current


    val imageResource = when (result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Button(
            onClick = { result = (1..6).random() //armazena o valor novo do dado depois de ter rodado, e é chamdo a função de verificação
                resultado = verificacao(escolha.toInt(), result)
                resultado.value = resultado //atualizando a variável
                Toast.makeText(context, resultado, Toast.LENGTH_SHORT).show()//resultado é apresentado em um Toast
            }, ) {
            Text(stringResource(R.string.roll))
        }
        Spacer(
            modifier = Modifier.height(16.dp))
        OutlinedTextField(//caixa de texto
            value = escolha,
            onValueChange = {escolha = it},
            label = { Text("Digite sua tentativa:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)//só aceita valor numérico
        )

    }

}
//função para a verificação da imagem do dado

fun verificacao(escolha: Int, nunDado: Int): String {

    if(escolha == nunDado){
        return "Você acertou!"
    } else {
        return "Você errou!"
    }

}
