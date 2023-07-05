package com.antosik.benchproject.app.common.binding.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.antosik.benchproject.app.common.recyclerView.RecyclerBaseAdapter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class RecyclerViewBindingAdaptersKtTest {
    val recyclerBaseAdapter: RecyclerBaseAdapter<Any> = mockk(relaxed = true)
    val recyclerView: RecyclerView = mockk {
        every { adapter } returns recyclerBaseAdapter
    }

    @Test
    fun `SHOULD invoke method uploadData WHEN input data is NOT null`() {
        val data: List<Any> = mockk(relaxed = true)

        recyclerView.updateAdapterData(data)

        verify { recyclerBaseAdapter.uploadData(data) }
    }

    @Test
    fun `SHOULD don't invoke method uploadData WHEN input data is null`() {
        recyclerView.updateAdapterData(null)

        verify(exactly = 0) { recyclerBaseAdapter.uploadData(any()) }
    }
}
