# Patchwork Health Technical Test

## Implementation

My initial setup attempts failed, so I
followed [create-a-project](https://kotlinlang.org/docs/jvm-get-started.html#create-a-project), which produced a flat
project structure without `src` and `test`. Hacking achieved the standard project structure that I would have expected.

I then discovered that packaging is simply a placeholder, not a directory structure.

### Assumptions/decisions

- Starting with finding books by author, I've assumed it should be case insensitive.
- I decided on `data class BookRepo` so I can have a more functional interface - initially I had tried an object.
- Author is full name and not separated first and last name
- "library user" and "find books by my favourite author" may imply I need to model a user with favourites, but as it's
  not explicitly asked for, I've omitted it because I should still be able to report on outstanding books.
- I discovered that the ISBN format does not include letters. I will continue to represent it as a simple string without
  validation/formatting.
- After some thought, I altered the title and author search to use contain rather than an exact match.
- Moving on to the library operations, I considered separating the books from their status, this would allow book
  duplicates etc, but that's out of scope, so I'm keeping status as part of the book model.
- I'm assuming that "if they are available in the library" means they're visible in searches, but not for borrowing.
- Initially I had designed find books by ISBN to be a single book. I think this may have been in error, but for time's sake, I'm
  leaving the implementation as it is for now so I can get to more interesting things.
- To aid in borrowing and general repo ops, I added an id.

## Instructions

- [x] Please create a GitHub repository containing code for a book lending library. Use the stories below for
  requirements.
- [x] This is just a text-based exercise - no GUI code is required.
- [x] We also don’t expect a DB or persistence layer - storing data in memory is fine. Just prove it works by calling
  the relevant functions from other code.
- [x] Please don’t spend any more than 2 to 3 hours on this. We don’t expect a complete solution, but something that we
  can use as the basis for a follow-on conversation.
- [x] When you are ready, please send us a link to your repository.

### Context

I have many books which I would like to share with my community. That sounds like a book-lending library. Please write
some software to help me do that.

### Stories

- [x] As a library user, I would like to be able to find books by my favourite author, so that I know if they are
  available in the library.
- [x] As a library user, I would like to be able to find books by title, so that I know if they are available in the
  library.
- [x] As a library user, I would like to be able to find books by ISBN, so that I know if they are available in the
  library. In correctly implemented in that it's currently by exact match.
- [ ] As a library user, I would like to be able to borrow a book, so I can read it at home.
- [ ] As the library owner, I would like to know how many books are being borrowed, so I can see how many are
  outstanding.
- [ ] As a library user, I should be to prevented from borrowing reference books, so that they are always available.