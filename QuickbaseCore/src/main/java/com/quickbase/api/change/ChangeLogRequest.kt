package com.quickbase.api.change

import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ChangeLogRequest constructor(@NotNull val change: Change,
                                        @NotNull val author: String,
                                        @NotEmpty val contexts: Collection<String>,
                                        val changeSetId: String)
