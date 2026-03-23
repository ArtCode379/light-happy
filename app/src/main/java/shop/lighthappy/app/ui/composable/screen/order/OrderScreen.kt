package shop.lighthappy.app.ui.composable.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.lighthappy.app.R
import shop.lighthappy.app.data.entity.OrderEntity
import shop.lighthappy.app.ui.composable.shared.IKEHPContentWrapper
import shop.lighthappy.app.ui.composable.shared.IKEHPEmptyView
import shop.lighthappy.app.ui.state.DataUiState
import shop.lighthappy.app.ui.theme.Accent
import shop.lighthappy.app.ui.theme.MutedText
import shop.lighthappy.app.ui.theme.Primary
import shop.lighthappy.app.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        IKEHPContentWrapper(
            dataState = ordersState,
            dataPopulated = {
                val orders = (ordersState as DataUiState.Populated).data
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                ) {
                    items(orders, key = { it.orderNumber }) { order ->
                        OrderCard(order = order)
                    }
                }
            },
            dataEmpty = {
                IKEHPEmptyView(
                    primaryText = stringResource(R.string.orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun OrderCard(order: OrderEntity) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.order_number, order.orderNumber),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary,
                )

                Text(
                    text = "Confirmed",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2E7D32),
                    modifier = Modifier
                        .background(
                            Color(0xFF2E7D32).copy(alpha = 0.1f),
                            RoundedCornerShape(12.dp),
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.timestamp.format(dateFormatter),
                fontSize = 12.sp,
                color = MutedText,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.order_customer, order.customerFirstName, order.customerLastName),
                fontSize = 13.sp,
                color = MutedText,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.description,
                fontSize = 13.sp,
                color = Primary.copy(alpha = 0.7f),
                maxLines = 2,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = String.format("Total: £%.2f", order.price),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Accent,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Collect within 24 hours",
                fontSize = 12.sp,
                color = MutedText,
            )
        }
    }
}
