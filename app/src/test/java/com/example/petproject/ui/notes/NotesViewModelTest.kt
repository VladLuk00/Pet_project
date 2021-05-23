package com.example.petproject.ui.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.petproject.data.db.NoteDao
import com.example.petproject.data.repository.NotesRepository
import com.example.petproject.utils.LiveDateUtils.blockingObserve
import com.example.petproject.utils.MockUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
class NotesViewModelTest {

    private lateinit var noteRepository: NotesRepository
    private lateinit var noticeViewModel: NoticeViewModel
    private var noteDao: NoteDao = mock(NoteDao::class.java)
    private var testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        noteRepository = NotesRepository(noteDao)
        noticeViewModel = NoticeViewModel(noteRepository)

        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `is listOfNotices after trigger setNotes() return list`() = runBlocking {
        given(noteRepository.listOfNotes()).willReturn(MockUtil.getListOfNotes())
        noticeViewModel.setNotices()
        val actual = noticeViewModel.listOfNotices.blockingObserve()
        Assert.assertEquals(MockUtil.getListOfNotes(), actual)
    }
}