package com.antosik.benchproject.app.more.view

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antosik.benchproject.R
import com.antosik.benchproject.app.more.viewModel.MoreViewModel

@Composable
fun MoreScreen(moreViewModel: MoreViewModel) {
    Screen(
        onClickLicenses = moreViewModel::onLicensesClick,
        onClickGithub = moreViewModel::onGithubClick,
        onClickLinkedin = moreViewModel::onLinkedinClick
    )
}

@Composable
private fun Screen(
    onClickLicenses: () -> Unit,
    onClickGithub: () -> Unit,
    onClickLinkedin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 58.dp)
    ) {
        LabelText(textResourceId = R.string.descriptionMoreFragmentLabel)
        DescriptionText(textResourceId = R.string.descriptionMoreFragmentText)
        GreyDivider()
        LabelText(textResourceId = R.string.licensesMoreFragmentLabel)
        TextButtonWithTextResource(
            textResourceId = R.string.showLicensesLabel,
            onClick = onClickLicenses,
        )
        GreyDivider()
        LabelText(textResourceId = R.string.aboutMeMoreFragmentLabel)
        IconAndTextButtonWithTextResource(
            textResourceId = R.string.githubLabel,
            iconResourceId = R.drawable.ic_github_logo,
            onClick = onClickGithub,
        )
        IconAndTextButtonWithTextResource(
            textResourceId = R.string.linkedinLabel,
            iconResourceId = R.drawable.ic_linkedin_logo,
            onClick = onClickLinkedin,
        )
    }
}

@Composable
private fun LabelText(
    @StringRes textResourceId: Int,
) {
    Text(
        text = stringResource(id = textResourceId),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        color = MaterialTheme.colors.onPrimary,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun DescriptionText(
    @StringRes textResourceId: Int,
) {
    Text(
        text = stringResource(id = textResourceId),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        color = MaterialTheme.colors.onPrimary,
        fontSize = 16.sp,
        textAlign = TextAlign.Justify,
    )
}

@Composable
private fun TextButtonWithTextResource(
    textResourceId: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.align(Alignment.Center),
        ) {
            Text(
                text = stringResource(id = textResourceId).uppercase(),
                color = MaterialTheme.colors.primary,
            )
        }
    }
}

@Composable
private fun IconAndTextButtonWithTextResource(
    textResourceId: Int,
    iconResourceId: Int,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = iconResourceId),
            contentDescription = null,
            modifier = Modifier.size(34.dp),
        )
        TextButton(
            onClick = onClick,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(90.dp),
        ) {
            Text(
                text = stringResource(id = textResourceId).uppercase(),
                color = MaterialTheme.colors.primary,
            )
        }
    }
}

@Composable
private fun GreyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, start = 12.dp, end = 12.dp),
        color = MaterialTheme.colors.onPrimary,
        thickness = 1.dp,
    )
}

@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    Screen({}, {}, {})
}
