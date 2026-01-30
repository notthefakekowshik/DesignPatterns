
Here is how we will structure this discussion to match your "Step-by-Step" learning goal:

Requirements Gathering: Define what we are building.
Core Entities: Define the basic objects.
Brute Force Solution: Use simple lists and see why it fails.
Optimized Solution: Use proper data structures (Priority Queues/Treemaps).
Concurrency: Make it thread-safe.
Single Machine, not distributed.

!! We should never check for user funds validation, OrderEngine is a crucial service where every ns matters, we can't make calls to DB to validate funds.

"I am assuming there is an upstream Risk Management Layer that handles pre-trade validation (KYC, Funds, Margin limits).
By the time an Order object reaches my processOrder method, I treat it as 'Good for Execution'.
My engine will focus purely on latency and matching logic."


Requirements
Functional requirements
1. User should be able to place buy and sell orders

Non functional requirements:
1. Concurrent
2. Latency
3. In-memory

