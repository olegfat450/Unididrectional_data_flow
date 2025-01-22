package com.example.unididrectional_data_flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unididrectional_data_flow.ui.theme.Unididrectional_data_flowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {

          val height = remember {  mutableStateOf(165) }
            val weight = remember { mutableStateOf(50) }

           val imt = remember { derivedStateOf { (weight.value.toDouble() / (height.value.toDouble()/100 * height.value.toDouble()/100)) } }

               val result = remember { derivedStateOf {


                   when (imt.value){

                     in 0.0..< 16.0 -> "Дефицит массы тела"
                     in 16.0..< 18.5 -> "Недостаточная масса тела"
                     in 18.5..< 25.0 -> "Нормальная масса тела"
                     in 25.0 ..< 30.0 -> "Избыточная масса тела"
                     in 30.0..< 35.0 -> "Ожирение 1-ой степени"
                     in 35.0..< 40.0 -> "Ожирение 2-й степени"
                       else -> "Ожирение 3-й степени"

                   }



               } }

            Column (modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
                .background(Color.LightGray)) {

                Text(text = "Калькулятор ИМТ", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.LightGray,
                    textAlign = TextAlign.Center,modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(6.dp))


                Text(text = "Рост", fontSize = 24.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp))

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp), horizontalArrangement = Arrangement.SpaceAround) {

                      Text(text = "-", fontSize = 36.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clickable (onClick = {

                          if (height.value > 100) height.value -= 5}))
                      Text(text = height.value.toString(), fontSize = 20.sp, modifier = Modifier.padding(start = 32.dp, end = 32.dp))
                      Text(text = "+", fontSize = 36.sp, modifier = Modifier.clickable(onClick = {
                          if (height.value < 230) height.value += 5})) }


                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black))

                Text(text = "Вес", fontSize = 24.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp))



                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp), horizontalArrangement = Arrangement.SpaceAround) {

                    Text(text = "-", fontSize = 36.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clickable (onClick = {

                        if (weight.value > 30) weight.value -= 5}))
                    Text(text = weight.value.toString(), fontSize = 20.sp, modifier = Modifier.padding(start = 32.dp, end = 32.dp))
                    Text(text = "+", fontSize = 36.sp, modifier = Modifier.clickable(onClick = {
                        if (weight.value < 300) weight.value += 5})) }

                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black))

                Text(text = "Коэфициент ИМТ", fontSize = 20.sp, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 32.dp))
                  Text(text = imt.value.toString().take(4), fontSize = 38.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 12.dp))

                  Text(text = result.value.toString(), fontSize = 24.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp, top = 26.dp)
                      .background(Color.White, shape = RoundedCornerShape(12.dp)).padding(6.dp)



                  )


            }



        }



    }
}