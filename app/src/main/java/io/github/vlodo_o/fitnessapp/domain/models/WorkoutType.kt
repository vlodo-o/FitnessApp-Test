package io.github.vlodo_o.fitnessapp.domain.models

enum class WorkoutType(val value: Int) {
    TRAINING(1),
    LIVE(2),
    COMPLEX(3);

    override fun toString(): String = when(this) {
        TRAINING -> "Тренировка"
        LIVE -> "Эфир"
        COMPLEX -> "Комплекс"
    }

    companion object {
        fun fromInt(value: Int): WorkoutType {
            return WorkoutType.entries.firstOrNull { it.value == value } ?: TRAINING
        }
    }
}