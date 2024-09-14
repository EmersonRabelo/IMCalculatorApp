package br.com.fiap.helloworld

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.helloworld.ui.theme.HelloWorldTheme
import br.com.fiap.helloworld.ui.theme.Lato

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalcIMC()
                }
            }
        }
    }
}

@Composable
fun CalcIMC() {

    var weight by remember {
        mutableStateOf("")
    }

    var height by remember {
        mutableStateOf("")
    }

    var imcResult by remember {
        mutableStateOf(0.0)
    }

    var imcStatus by remember {
        mutableStateOf(Pair(0,""))
    }

    val resultCardColor: Color = if (imcStatus.first == 1) {
        Color(0xFF329F6B)
    } else if(imcStatus.first == 2) {
        Color(0xFFC7461D)
    } else if(
        imcStatus.first == 0 ||
        imcStatus.first == 3 ||
        imcStatus.first == 4 ||
        imcStatus.first == 5
    ) {
        Color.Red
    } else {
        Color(0xFFFFFFFF)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(colorResource(id = R.color.vermelho_fiap)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_imc),
                contentDescription = "Logo do aplicativo",
                modifier = Modifier
                    .size(90.dp, 90.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Calculadora IMC",
                color = colorResource(id = R.color.text_color)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .height(340.dp)
                    .fillMaxWidth()
                    .offset(y = (140).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    hoveredElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(
                        vertical = 16.dp,
                        horizontal = 32.dp
                    )
                ) {
                    Text(
                        text = "Seus dados",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.vermelho_fiap),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Seu peso",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.vermelho_fiap)
                    )
                    OutlinedTextField(
                        value = weight,
                        onValueChange = {
                            weight = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Seu peso em Kg.")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                            focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Sua altura",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.vermelho_fiap)
                    )
                    OutlinedTextField(
                        value = height,
                        onValueChange = {
                            height = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Sua altura em cm."
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                            focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            imcResult = calcularIMC(
                                weight.toDouble(),
                                height.toDouble()
                            )
                            imcStatus = statusIMC(imcResult)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.vermelho_fiap)
                        )
                    ) {
                        Text(
                            text = "CALCULAR",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = resultCardColor,
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column() {
                    Text(
                        text = "Resultado",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Lato
                    )
                    Text(
                        text = imcStatus.second,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Lato
                    )
                }
                Text(
                    text = String.format("%.2f", imcResult),
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Lato
                )
            }
        }
    }
}

@Composable
fun TextComponent() {

    var text by remember {
        mutableStateOf("")
    }

    var birthday by remember {
        mutableStateOf("")
    }

    var isStudent by remember {
        mutableStateOf(false)
    }

    var isGraduated by remember {
        mutableStateOf(false)
    }

    val rainbowColors = listOf<Color>(
        Color(0xFFFF0000), // Vermelho
        Color(0xFFFF7F00), // Laranja
        Color(0xFFFFFF00), // Amarelo
        Color(0xFF00FF00), // Verde
        Color(0xFF0000FF), // Azul
        Color(0xFF4B0082), // Anil (Índigo)
        Color(0xFF8B00FF)  // Violeta
    )

    val brush = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }

    var buttoncolor by remember {
        mutableStateOf(1)
    }

    Column(

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xFF5912AB))
                    .border(border = BorderStroke(1.dp, Color.Black))
                    //.padding(start = 4.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)
                    //.align(Alignment.Start)
                    .padding(horizontal = 15.dp, vertical = 4.dp),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 2.sp,
                text = "Estudando Android Kotlin",
                //fontFamily = FontFamily.Serif
                fontFamily = Lato
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.background(Color.Black),
                label = {
                    Text(text = "Oque está estudando?")
                },
                value = text,
                //onValueChange = {text = it},
                onValueChange = { term -> text = term },
                textStyle = TextStyle(
                    brush = brush
                ),
                singleLine = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_flutter_dash_24),
                        contentDescription = "icone de busca",
                        tint = Color(0xFF8B00FF)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                label = {
                    Text(text = "Telefone")
                },
                value = birthday,
                onValueChange = { birthday = it },
                textStyle = TextStyle(
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                minLines = 1,
//                leadingIcon = {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_cake_24),
//                        contentDescription = "icone de busca"
//                    )
//                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "icone de busca"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF8B00FF),
                    focusedLabelColor = Color(0xFF8B00FF),
                    cursorColor = Color(0xFF8B00FF)
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isStudent,
                    onCheckedChange = {
                        isStudent = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF8B00FF),
                        checkmarkColor = Color(0xFF15BE28),
                        uncheckedColor = Color(0xFF8B00FF)
                    )
                )
                Text(text = "É estudante?")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isGraduated,
                    onClick = {
                        isGraduated = !isGraduated
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF15BE28),
                        unselectedColor = Color(0xFF8B00FF)
                    )
                )
                Text(text = "É formado?")
            }
            Spacer(modifier = Modifier.height(12.dp))

            var theme by remember {
                mutableStateOf(0) // -1 : Evita que a primeira seja marcada
            }

            Column(

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = theme == 0,
                        onClick = {
                            theme = 0
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF15BE28),
                            unselectedColor = Color(0xFF8B00FF)
                        )
                    )
                    Text(text = "Dark")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = theme == 1,
                        onClick = {
                            theme = 1
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF8B00FF),
                            unselectedColor = Color(0xFF15BE28)
                        )
                    )
                    Text(text = "Light")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp),
                    onClick = {
                        if (buttoncolor <= rainbowColors.size) {
                            buttoncolor += 1
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = rainbowColors.get(buttoncolor),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, Color(0xFF00FA1C)),
                    shape = CircleShape
                ) {
                    Row(
                        modifier = Modifier.width(120.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Build, contentDescription = "Mudar Cor")
                        Text(text = "Mudar")
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF00FA1C)
                    ),
                    border = BorderStroke(2.dp, Color(0xFF8B00FF))
                ) {
                    Row(
                        modifier = Modifier.width(120.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Compartilhar")
                        Text(text = "Compartilhar")
                    }
                }
            }
        }

    }


}

@Composable
fun BoxComponent() {
    Box(
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "CLICA VAI")
                }
            }
            Text(text = "Fiap 1")
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Fiap 2")
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Fiap 3")
        }
        Button(
            onClick = { /*TODO*/ },
            shape = CircleShape,
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .offset(x = 152.dp, y = 280.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF88c6a5),
                contentColor = Color.Black
            ),
        ) {
            Icon(
                modifier = Modifier.size(width = 12.dp, height = 12.dp),
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Novo recurso."
            )
        }
        Button(
            onClick = { /*TODO*/ },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .width(62.dp)
                .height(62.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF88c6a5),
                contentColor = Color.White
            ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Novo recurso."
            )
        }
    }
}

@Composable
fun FirstComponent() {

    var age: MutableState<Int> = remember {
        mutableIntStateOf(18)
    }

    val msgIsOlder: String = if (age.value >= 18) {
        "Você é MAIOR de idade!"
    } else {
        "Você é MENOR de idade!"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Qual a sua idade?",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Selecione sua idade",
            fontSize = 14.sp,
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "${age.value}",
            fontSize = 48.sp,
            color = Color.Red,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    age.value--;
                    if (age.value < 0) {
                        age.value = 0;
                    }
                    Log.i("Fiap", "Value: ${age.value}")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF88c6a5),
                    contentColor = Color.White
                ),
                modifier = Modifier.size(84.dp),
                shape = RectangleShape
            ) {
                Text(text = "-")
            }
            Button(
                onClick = {
                    age.value++;
                    if (age.value > 130) {
                        age.value = 130;
                    }
                    Log.i("Fiap", "Value: ${age.value}")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF88c6a5),
                    contentColor = Color.White
                ),
                modifier = Modifier.size(84.dp),
                shape = RectangleShape
            ) {
                Text(text = "+")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = msgIsOlder,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            placeholder = {
                Text(text = "Oque você deseja buscar hoje?")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_cake_24),
                    contentDescription = "Botão de busca"
                )
            },
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        CalcIMC()
    }
}