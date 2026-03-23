package shop.lighthappy.app.ui.composable.screen.productdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.lighthappy.app.R
import shop.lighthappy.app.data.model.Product
import shop.lighthappy.app.ui.composable.shared.IKEHPContentWrapper
import shop.lighthappy.app.ui.composable.shared.IKEHPEmptyView
import shop.lighthappy.app.ui.state.DataUiState
import shop.lighthappy.app.ui.theme.Accent
import shop.lighthappy.app.ui.theme.MutedText
import shop.lighthappy.app.ui.theme.Primary
import shop.lighthappy.app.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        IKEHPContentWrapper(
            dataState = productState,
            modifier = Modifier.weight(1f),
            dataPopulated = {
                val product = (productState as DataUiState.Populated).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                ) {
                    Image(
                        painter = painterResource(product.imageRes),
                        contentDescription = stringResource(R.string.product_image_description),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                        contentScale = ContentScale.Crop,
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = product.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = stringResource(product.category.titleRes),
                            fontSize = 12.sp,
                            color = Accent,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .background(
                                    Accent.copy(alpha = 0.1f),
                                    RoundedCornerShape(12.dp),
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = String.format("£%.2f", product.price),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Accent,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = product.description,
                            fontSize = 14.sp,
                            color = MutedText,
                            lineHeight = 22.sp,
                        )
                    }
                }
            },
            dataEmpty = {
                IKEHPEmptyView(
                    primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )

        Button(
            onClick = onAddToCart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White,
            ),
        ) {
            Text(
                text = stringResource(R.string.button_add_to_cart_label),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
