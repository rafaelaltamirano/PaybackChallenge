package com.example.paybackchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.paybackchallenge.R
import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.enum.Escale.*
import com.example.paybackchallenge.ui.theme.Danger
import com.example.paybackchallenge.ui.theme.Success
import com.example.paybackchallenge.ui.theme.Warning


@Composable
fun LazyItemsRow(car: Car?) {
    val carList = car?.statistics ?: emptyList()


    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(carList) {
            val value: Int
            val escale: String
            val icon: Int
            val color: Color

            when (it.scale) {
                VOLTAGE -> {
                    value = R.string.voltage
                    escale = stringResource(R.string.volt)
                    icon = R.drawable.ic_car_battery_solid
                    color = Danger
                }
                REMAINING_CHARGE -> {
                    value = R.string.remaining_charge
                    escale = stringResource(R.string.km)
                    icon = R.drawable.ic_battery_three_quarters_solid
                    color = Success
                }
                FAST_CHARGE -> {
                    value = R.string.fast_charge
                    escale = stringResource(R.string.min)
                    icon = R.drawable.ic_plug_circle_bolt_solid
                    color = Warning
                }
            }

            StaticItem(
                title ="${it.quantity} $escale",
                subtitle = stringResource(value),
                image = "data:image/jpeg;base64,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",
                color = color,
                itemModifier = Modifier
            )
        }
    }
}

@Composable
fun StaticItem(title: String, subtitle: String, image: String, color: Color, itemModifier: Modifier) {

    Card(
        shape = RoundedCornerShape(10), elevation = 4.dp,
        modifier = itemModifier,
        border = BorderStroke(5.dp, Color.White), backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
            Row(Modifier.padding(10.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(image),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
            }
            Column(Modifier.padding(10.dp)) {
                Text(
                    text = title,
                    color = Color.Black,
                    style = MaterialTheme.typography.h3,
                )
            }
        }
    }

}