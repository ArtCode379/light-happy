package shop.lighthappy.app.ui.composable.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shop.lighthappy.app.R
import shop.lighthappy.app.ui.composable.shared.IKEHPContentWrapper
import shop.lighthappy.app.ui.composable.shared.IKEHPEmptyView
import shop.lighthappy.app.ui.state.CartItemUiState
import shop.lighthappy.app.ui.state.DataUiState
import shop.lighthappy.app.ui.theme.Accent
import shop.lighthappy.app.ui.theme.Divider
import shop.lighthappy.app.ui.theme.MutedText
import shop.lighthappy.app.ui.theme.Primary
import shop.lighthappy.app.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    val onPlusItemClick = { itemId: Int ->
        viewModel.incrementProductInCart(itemId)
    }

    val onMinusItemClick = { itemId: Int ->
        viewModel.decrementItemInCart(itemId)
    }

    val onDeleteItemClick = { itemId: Int ->
        viewModel.deleteFromCart(itemId)
    }

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = onPlusItemClick,
        onMinusItemClick = onMinusItemClick,
        onDeleteItemClick = onDeleteItemClick,
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onDeleteItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        IKEHPContentWrapper(
            dataState = cartItemsState,
            modifier = Modifier.weight(1f),
            dataPopulated = {
                val items = (cartItemsState as DataUiState.Populated).data
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                ) {
                    items(items, key = { it.productId }) { item ->
                        CartItemCard(
                            item = item,
                            onPlusClick = { onPlusItemClick(item.productId) },
                            onMinusClick = { onMinusItemClick(item.productId) },
                            onDeleteClick = { onDeleteItemClick(item.productId) },
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = Divider)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Subtotal",
                                fontSize = 14.sp,
                                color = MutedText,
                            )
                            Text(
                                text = String.format("£%.2f", totalPrice),
                                fontSize = 14.sp,
                                color = Primary,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Total",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Primary,
                            )
                            Text(
                                text = String.format("£%.2f", totalPrice),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Primary,
                            )
                        }
                    }
                }
            },
            dataEmpty = {
                IKEHPEmptyView(
                    primaryText = stringResource(R.string.cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )

        if (cartItemsState is DataUiState.Populated) {
            Button(
                onClick = onCompleteOrderButtonClick,
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
                    text = stringResource(R.string.button_place_order_label, totalPrice),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
private fun CartItemCard(
    item: CartItemUiState,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            item.productImageRes?.let { imageRes ->
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = item.productTitle,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.productTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Primary,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = String.format("£%.2f", item.productPrice),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Accent,
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onMinusClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.minus_svgrepo_com),
                        contentDescription = stringResource(R.string.decrease_quantity_icon_description),
                        modifier = Modifier.size(16.dp),
                        tint = Primary,
                    )
                }

                Text(
                    text = "${item.quantity}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Primary,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                IconButton(onClick = onPlusClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.plus_svgrepo_com),
                        contentDescription = stringResource(R.string.increase_quantity_icon_description),
                        modifier = Modifier.size(16.dp),
                        tint = Primary,
                    )
                }

                IconButton(onClick = onDeleteClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.trash_svgrepo_com),
                        contentDescription = stringResource(R.string.delete_item_icon_description),
                        modifier = Modifier.size(16.dp),
                        tint = Accent,
                    )
                }
            }
        }
    }
}
