package com.antosik.benchproject.app.common.compose.placeholder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antosik.benchproject.R

@Preview
@Composable
fun EmptyDatabase(
    modifier: Modifier = Modifier,
    textResourceId: Int = R.string.emptyDatabaseText,
    imageHeight: Int = 220,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_empty_data),
                contentDescription = null,
                modifier = Modifier
                    .height(imageHeight.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = textResourceId),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}