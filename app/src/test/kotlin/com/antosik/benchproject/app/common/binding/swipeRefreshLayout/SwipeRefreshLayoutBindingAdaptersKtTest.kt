package com.antosik.benchproject.app.common.binding.swipeRefreshLayout

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class SwipeRefreshLayoutBindingAdaptersKtTest {

    val swipeRefreshLayout: SwipeRefreshLayout = mockk(relaxed = true)

    @TestFactory
    fun `SHOULD isRefreshing is or isn't invoke WHEN isRefreshed() called with specific value`() =
        listOf(
            Param(
                actual = false,
                expected = false,
                exactly = 0
            ),
            Param(
                actual = true,
                expected = false,
                exactly = 1
            )
        ).map { param ->
            DynamicTest.dynamicTest(
                "SHOULD isRefreshing invoke: " + param.exactly +
                    " WHEN isRefreshed(" + param.actual + ")"
            ) {
                swipeRefreshLayout.isRefreshed(param.actual)

                verify(exactly = param.exactly) { swipeRefreshLayout.isRefreshing = param.expected }
                clearMocks(swipeRefreshLayout)
            }
        }
}

data class Param(
    val actual: Boolean,
    val expected: Boolean,
    val exactly: Int,
)
