package com.example.petproject.ui.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.petproject.data.db.NoteDao
import com.example.petproject.data.repository.NotesRepository
import com.example.petproject.utils.LiveDateUtils.blockingObserve
import com.example.petproject.utils.MockUtil
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NoticeViewModelTest : TestCase() {

    private lateinit var noteRepository: NotesRepository
    private lateinit var noticeViewModel: NoticeViewModel
    private var noteDao: NoteDao = Mockito.mock(NoteDao::class.java)

    private var testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun prepare() {
        noteRepository = NotesRepository(noteDao)
        noticeViewModel = NoticeViewModel(noteRepository)

        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun set() = runBlocking {
        BDDMockito.given(noteRepository.listOfNotes()).willReturn(MockUtil.getListOfNotes())
        noticeViewModel.setNotices()
        val actual = noticeViewModel.listOfNotices.blockingObserve()
        Assert.assertEquals(MockUtil.getListOfNotes(), actual)
    }
}