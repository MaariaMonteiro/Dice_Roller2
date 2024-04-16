package com.example.dice_roller

import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dice_roller.ui.theme.Dice_RollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dice_RollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
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
    var result by remember { mutableStateOf( 1) }
    var aposta by remember { mutableStateOf("") }
    var acerto by remember { mutableStateOf("") }
    val context = LocalContext.current


    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        Button(
            onClick = { result = (1..6).random()
                acerto = verificacao(aposta.toInt(), result)
                Toast.makeText(context,acerto, Toast.LENGTH_SHORT).show()
            },
        ) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = aposta,
            onValueChange = {aposta = it},
            label = { Text("Insira o valor da sua aposta:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

    }
}

fun verificacao(v_aposta: Int, n_dado: Int): String {
    if(v_aposta in 1..6) {
        if(v_aposta == n_dado) {
            return  "Você acertou o resultado! Parabéns."
        } else {
            return "Infelizmente você não acertou o resultado. Tente novamente!"
        }
    } else {
        return "As apostas devem ser entre entre 1 e 6."
    }
}