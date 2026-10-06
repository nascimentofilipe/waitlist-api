# ADR 001: Queue ordering

## Status

Accepted

## Context

Elective surgery requests are prioritized using the SWALIS model, which has
five categories (A1, A2, B, C, D), each with a maximum waiting time.

The protocol describes two ways to order the queue that conflict with each other:

- by category first, then by waiting time;
- by a score: days waiting × urgency coefficient.

Using either one alone causes a problem. Ordering only by category means a D
patient may wait forever. Ordering only by the score means a newly added A1
patient (0 days waiting, score 0) would stay behind a D patient who has been
waiting for weeks, even though A1 means risk of imminent clinical deterioration.

## Options considered

1. **Category first, then waiting time.** Simple, but lower categories may
   never be served while higher-category requests keep arriving.
2. **Score only** (days waiting × coefficient, where coefficient = 360 ÷ maximum
   waiting days). Fair to deadlines, but a new A1 patient starts at the bottom.
3. **Hybrid.** A1 requests always come first; all other requests are ordered
   by score.

## Decision

We chose option 3 (hybrid).

- A1 requests bypass the score and always come first, because of clinical risk.
- Among A1 requests, the one waiting longest comes first.
- All other requests are ordered by score, highest first.
- When two requests have the same score, the one that entered the queue
  first comes first.

The score measures how much of the maximum waiting time has already been used,
so requests closer to their deadline move up.

## Consequences

- The A1 queue must be monitored separately, since it is not limited by the score.
- If a patient is reclassified (for example, from C to A2), the entry date stays
  the same and only the coefficient changes, so the patient moves up immediately.
- The rule lives in the `Priority` enum (`bypassesScoring()` and
  `getCoefficient()`) and will be applied by a domain service (`WaitlistRanking`).
  Any change to the ordering must update this ADR.