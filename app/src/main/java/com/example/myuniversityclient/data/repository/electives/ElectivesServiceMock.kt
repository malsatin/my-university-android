package com.example.myuniversityclient.data.repository.electives

import com.example.myuniversityclient.data.models.Elective
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

/**
 * Temporary [ElectivesService] implementation
 */
class ElectivesServiceMock : ElectivesService {
    override fun getElectives(onResult: (Result<List<Elective>>) -> Unit) {
        val formatter = DateTimeFormatterBuilder()
            .appendPattern("dd-MM-yyyy")
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(ZoneId.systemDefault())

        onResult(
            Result.success(
                listOf(
                    Elective(
                        formatter.parse("27-07-2017", Instant::from),
                        "Entrepreneurship",
                        "eSports industry: marketing, economy and game design"
                    ),
                    Elective(
                        formatter.parse("15-01-2019", Instant::from),
                        "Technical",
                        "Advanced С++: New Language Concepts, Features and Mechanisms"
                    ),
                    Elective(
                        formatter.parse("11-08-2019", Instant::from),
                        "Technical",
                        "Reverse Engineering"
                    ),
                    Elective(
                        formatter.parse("11-08-2019", Instant::from),
                        "Technical",
                        "Advanced Topics in Software Testing and Quality Management"
                    ),
                    Elective(
                        formatter.parse("09-08-2018", Instant::from),
                        "Technical",
                        "Human-Computer Interaction Design for AI"
                    ),
                    Elective(
                        formatter.parse("17-01-2020", Instant::from),
                        "Technical",
                        "Design Patterns"
                    ),
                    Elective(
                        formatter.parse("17-01-2020", Instant::from),
                        "Technical",
                        "Consensus theory and concurrent programming on a shared memory"
                    )
                )
            )
        )
    }
}