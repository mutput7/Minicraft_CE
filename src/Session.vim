let SessionLoad = 1
if &cp | set nocp | endif
let s:cpo_save=&cpo
set cpo&vim
imap <F11> 1G=Ga
vnoremap <silent>  :call RangeCommentLine()
nnoremap <silent>  :call CommentLine()
onoremap <silent>  :call CommentLine()
map  :call ToggleHex()
map <silent>  :set invhlsearch
vnoremap <silent>  :call RangeUnCommentLine()
nnoremap <silent>  :call UnCommentLine()
onoremap <silent>  :call UnCommentLine()
map  :tab split:exec("tag ".expand("<cword>"))
nnoremap   za
map ! :w
nmap <silent> ,b :CommandTBuffer
nmap <silent> ,t :CommandT
map ,c :call OpenCFiles()
map ,e :tabe 
imap ð :call SearchInvalidComment(1)a
imap î :call SearchInvalidComment(0)a
imap ã :call JCommentWriter()
map \w :NERDTreeToggle
nmap gx <Plug>NetrwBrowseX
nnoremap <silent> <Plug>NetrwBrowseX :call netrw#NetrwBrowseX(expand("<cWORD>"),0)
map <F3> :mksession!
map <F4> :Lodgeit
map <F2> :set spell!
map <F8> :Tlist
map <silent> <F12> :wa:silent make:cw:cw:redraw!
nmap <F11> 1G=G
imap 	 =Smart_TabComplete()
inoremap  u
map ð :call SearchInvalidComment(1)
map î :call SearchInvalidComment(0)
map ã :call JCommentWriter()
nmap è <<
nmap ì >>
nmap ë ddkP
nmap ê ddp
iabbr }- }h%?\w:nohl:call JCommentWriter()
let &cpo=s:cpo_save
unlet s:cpo_save
set autoindent
set backspace=indent,eol,start
set backup
set backupdir=~/.vim/backup/
set completefunc=VjdeCompletionFun0
set completeopt=longest,menuone,preview
set fileencodings=ucs-bom,utf-8,latin1
set formatoptions=tcql
set guicursor=n-v-c:block,o:hor50,i-ci:hor15,r-cr:hor30,sm:block,a:blinkon0
set helplang=fr
set ignorecase
set incsearch
set laststatus=2
set mouse=a
set omnifunc=syntaxcomplete#Complete
set ruler
set runtimepath=~/.vim,~/.vim/bundle/command-t,~/.vim/bundle/nerdtree,/usr/share/vim/vimfiles,/usr/share/vim/vim73,/usr/share/vim/vimfiles/after,~/.vim/after
set scrolloff=20
set shiftwidth=4
set showcmd
set smartcase
set smartindent
set smarttab
set spelllang=fr,en
set statusline=%F%m%r%h%w\ [TYPE=%Y\ %{&ff}]\ [%l/%L\ (%p%%)]
set suffixes=.bak,~,.o,.h,.info,.swp,.obj,.class
set switchbuf=usetab,newtab
set tabstop=4
set tags=./tags;/
set wildignore=*.o,*.pyc,*.obj,*.svnignore,*.class
let s:so_save = &so | let s:siso_save = &siso | set so=0 siso=0
let v:this_session=expand("<sfile>:p")
silent only
cd ~/Developments/minicraft/src
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
set shortmess=aoO
badd +97 com/mojang/ld22/Game.java
badd +36 com/mojang/ld22/level/Level.java
badd +27 com/mojang/ld22/screen/TitleMenu.java
badd +24 com/mojang/ld22/entity/Mob.java
badd +424 com/mojang/ld22/entity/Player.java
badd +15 com/mojang/ld22/entity/Entity.java
badd +11 com/mojang/ld22/entity/Inventory.java
badd +33 com/mojang/ld22/item/Item.java
badd +105 ../../thieves/server/Player.java
badd +62 ../../thieves/server/GameManager.java
badd +0 ../../thieves/server/Main.java
badd +0 ../../thieves/server/PlayerManager.java
silent! argdel *
edit com/mojang/ld22/Game.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
argglobal
nnoremap <buffer> <silent> ,jd :call VjdeGetDocUnderCursor()
nnoremap <buffer> <silent> ,as :call VjdeAppendTemplate("Singleton")
nnoremap <buffer> <silent> ,ii :call VjdeImplementInner()
nnoremap <buffer> <silent> ,oi :call Vjde_override(1)
nnoremap <buffer> <silent> ,oe :call Vjde_override(0)
vnoremap <buffer> <silent> ,en :call Vjde_rft_const()
nnoremap <buffer> <silent> ,ep :call Vjde_rft_arg()
nnoremap <buffer> <silent> ,em :call Vjde_rft_var(1)
nnoremap <buffer> <silent> ,el :call Vjde_rft_var(2)
nnoremap <buffer> <silent> ,ri :call Vjde_remove_imports()
nnoremap <buffer> <silent> ,ai :call Vjde_fix_import1()
nnoremap <buffer> <silent> ,fi :call Vjde_fix_import()
nnoremap <buffer> <silent> ,ft :call Vjde_fix_try()
nnoremap <buffer> <silent> ,fr :call Vjde_fix_throws()
vnoremap <buffer> <silent> ,js :call Vjde_sort_import()
vnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jc :call VjdeGenerateConstructor()
nnoremap <buffer> <silent> ,jg :call Vjde_get_set()
nnoremap <buffer> <silent> ,je :call Vjde_ext_import()
inoremap <buffer> ® =VjdeIabCompletionPUM()
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal cindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=j1
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=sO:*\ -,mO:*\ \ ,exO:*/,s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=//%s
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=VjdeCompletionFun0
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
setlocal nocursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != 'java'
setlocal filetype=java
endif
setlocal foldcolumn=0
setlocal foldenable
setlocal foldexpr=0
setlocal foldignore=#
setlocal foldlevel=0
setlocal foldmarker={{{,}}}
setlocal foldmethod=manual
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=croql
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=0
setlocal imsearch=0
setlocal include=
setlocal includeexpr=substitute(v:fname,'\\.','/','g')
setlocal indentexpr=GetJavaIndent()
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e,0=extends,0=implements
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=javacomplete#Complete
setlocal path=.,/usr/include,,
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal smartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=fr,en
setlocal statusline=
setlocal suffixesadd=.java
setlocal swapfile
setlocal synmaxcol=3000
if &syntax != 'java'
setlocal syntax=java
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal noundofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
silent! normal! zE
let s:l = 3 - ((2 * winheight(0) + 27) / 55)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
3
normal! 0
tabedit ../../thieves/server/PlayerManager.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
argglobal
nnoremap <buffer> <silent> ,jd :call VjdeGetDocUnderCursor()
nnoremap <buffer> <silent> ,as :call VjdeAppendTemplate("Singleton")
nnoremap <buffer> <silent> ,ii :call VjdeImplementInner()
nnoremap <buffer> <silent> ,oi :call Vjde_override(1)
nnoremap <buffer> <silent> ,oe :call Vjde_override(0)
vnoremap <buffer> <silent> ,en :call Vjde_rft_const()
nnoremap <buffer> <silent> ,ep :call Vjde_rft_arg()
nnoremap <buffer> <silent> ,em :call Vjde_rft_var(1)
nnoremap <buffer> <silent> ,el :call Vjde_rft_var(2)
nnoremap <buffer> <silent> ,ri :call Vjde_remove_imports()
nnoremap <buffer> <silent> ,ai :call Vjde_fix_import1()
nnoremap <buffer> <silent> ,fi :call Vjde_fix_import()
nnoremap <buffer> <silent> ,ft :call Vjde_fix_try()
nnoremap <buffer> <silent> ,fr :call Vjde_fix_throws()
vnoremap <buffer> <silent> ,js :call Vjde_sort_import()
vnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jc :call VjdeGenerateConstructor()
nnoremap <buffer> <silent> ,jg :call Vjde_get_set()
nnoremap <buffer> <silent> ,je :call Vjde_ext_import()
inoremap <buffer> ® =VjdeIabCompletionPUM()
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal cindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=j1
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=sO:*\ -,mO:*\ \ ,exO:*/,s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=//%s
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=VjdeCompletionFun0
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
setlocal nocursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != 'java'
setlocal filetype=java
endif
setlocal foldcolumn=0
setlocal foldenable
setlocal foldexpr=0
setlocal foldignore=#
setlocal foldlevel=0
setlocal foldmarker={{{,}}}
setlocal foldmethod=manual
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=croql
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=0
setlocal imsearch=0
setlocal include=
setlocal includeexpr=substitute(v:fname,'\\.','/','g')
setlocal indentexpr=GetJavaIndent()
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e,0=extends,0=implements
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=javacomplete#Complete
setlocal path=
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal smartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=fr,en
setlocal statusline=
setlocal suffixesadd=.java
setlocal swapfile
setlocal synmaxcol=3000
if &syntax != 'java'
setlocal syntax=java
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal noundofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
silent! normal! zE
let s:l = 37 - ((31 * winheight(0) + 27) / 55)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
37
normal! 027l
tabedit com/mojang/ld22/entity/Player.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
argglobal
nnoremap <buffer> <silent> ,jd :call VjdeGetDocUnderCursor()
nnoremap <buffer> <silent> ,as :call VjdeAppendTemplate("Singleton")
nnoremap <buffer> <silent> ,ii :call VjdeImplementInner()
nnoremap <buffer> <silent> ,oi :call Vjde_override(1)
nnoremap <buffer> <silent> ,oe :call Vjde_override(0)
vnoremap <buffer> <silent> ,en :call Vjde_rft_const()
nnoremap <buffer> <silent> ,ep :call Vjde_rft_arg()
nnoremap <buffer> <silent> ,em :call Vjde_rft_var(1)
nnoremap <buffer> <silent> ,el :call Vjde_rft_var(2)
nnoremap <buffer> <silent> ,ri :call Vjde_remove_imports()
nnoremap <buffer> <silent> ,ai :call Vjde_fix_import1()
nnoremap <buffer> <silent> ,fi :call Vjde_fix_import()
nnoremap <buffer> <silent> ,ft :call Vjde_fix_try()
nnoremap <buffer> <silent> ,fr :call Vjde_fix_throws()
vnoremap <buffer> <silent> ,js :call Vjde_sort_import()
vnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jc :call VjdeGenerateConstructor()
nnoremap <buffer> <silent> ,jg :call Vjde_get_set()
nnoremap <buffer> <silent> ,je :call Vjde_ext_import()
inoremap <buffer> ® =VjdeIabCompletionPUM()
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal cindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=j1
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=sO:*\ -,mO:*\ \ ,exO:*/,s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=//%s
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=VjdeCompletionFun0
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
setlocal nocursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != 'java'
setlocal filetype=java
endif
setlocal foldcolumn=0
setlocal foldenable
setlocal foldexpr=0
setlocal foldignore=#
setlocal foldlevel=0
setlocal foldmarker={{{,}}}
setlocal foldmethod=manual
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=croql
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=0
setlocal imsearch=0
setlocal include=
setlocal includeexpr=substitute(v:fname,'\\.','/','g')
setlocal indentexpr=GetJavaIndent()
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e,0=extends,0=implements
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=javacomplete#Complete
setlocal path=.,/usr/include,,
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal smartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=fr,en
setlocal statusline=
setlocal suffixesadd=.java
setlocal swapfile
setlocal synmaxcol=3000
if &syntax != 'java'
setlocal syntax=java
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal noundofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
silent! normal! zE
let s:l = 410 - ((26 * winheight(0) + 27) / 55)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
410
normal! 03l
tabedit com/mojang/ld22/entity/Inventory.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
argglobal
nnoremap <buffer> <silent> ,jd :call VjdeGetDocUnderCursor()
nnoremap <buffer> <silent> ,as :call VjdeAppendTemplate("Singleton")
nnoremap <buffer> <silent> ,ii :call VjdeImplementInner()
nnoremap <buffer> <silent> ,oi :call Vjde_override(1)
nnoremap <buffer> <silent> ,oe :call Vjde_override(0)
vnoremap <buffer> <silent> ,en :call Vjde_rft_const()
nnoremap <buffer> <silent> ,ep :call Vjde_rft_arg()
nnoremap <buffer> <silent> ,em :call Vjde_rft_var(1)
nnoremap <buffer> <silent> ,el :call Vjde_rft_var(2)
nnoremap <buffer> <silent> ,ri :call Vjde_remove_imports()
nnoremap <buffer> <silent> ,ai :call Vjde_fix_import1()
nnoremap <buffer> <silent> ,fi :call Vjde_fix_import()
nnoremap <buffer> <silent> ,ft :call Vjde_fix_try()
nnoremap <buffer> <silent> ,fr :call Vjde_fix_throws()
vnoremap <buffer> <silent> ,js :call Vjde_sort_import()
vnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jc :call VjdeGenerateConstructor()
nnoremap <buffer> <silent> ,jg :call Vjde_get_set()
nnoremap <buffer> <silent> ,je :call Vjde_ext_import()
inoremap <buffer> ® =VjdeIabCompletionPUM()
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal cindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=j1
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=sO:*\ -,mO:*\ \ ,exO:*/,s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=//%s
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=VjdeCompletionFun0
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
setlocal nocursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != 'java'
setlocal filetype=java
endif
setlocal foldcolumn=0
setlocal foldenable
setlocal foldexpr=0
setlocal foldignore=#
setlocal foldlevel=0
setlocal foldmarker={{{,}}}
setlocal foldmethod=manual
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=croql
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=0
setlocal imsearch=0
setlocal include=
setlocal includeexpr=substitute(v:fname,'\\.','/','g')
setlocal indentexpr=GetJavaIndent()
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e,0=extends,0=implements
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=javacomplete#Complete
setlocal path=.,/usr/include,,
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal smartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=fr,en
setlocal statusline=
setlocal suffixesadd=.java
setlocal swapfile
setlocal synmaxcol=3000
if &syntax != 'java'
setlocal syntax=java
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal noundofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
silent! normal! zE
let s:l = 22 - ((21 * winheight(0) + 27) / 55)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
22
normal! 024l
tabedit com/mojang/ld22/entity/Entity.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
argglobal
nnoremap <buffer> <silent> ,jd :call VjdeGetDocUnderCursor()
nnoremap <buffer> <silent> ,as :call VjdeAppendTemplate("Singleton")
nnoremap <buffer> <silent> ,ii :call VjdeImplementInner()
nnoremap <buffer> <silent> ,oi :call Vjde_override(1)
nnoremap <buffer> <silent> ,oe :call Vjde_override(0)
vnoremap <buffer> <silent> ,en :call Vjde_rft_const()
nnoremap <buffer> <silent> ,ep :call Vjde_rft_arg()
nnoremap <buffer> <silent> ,em :call Vjde_rft_var(1)
nnoremap <buffer> <silent> ,el :call Vjde_rft_var(2)
nnoremap <buffer> <silent> ,ri :call Vjde_remove_imports()
nnoremap <buffer> <silent> ,ai :call Vjde_fix_import1()
nnoremap <buffer> <silent> ,fi :call Vjde_fix_import()
nnoremap <buffer> <silent> ,ft :call Vjde_fix_try()
nnoremap <buffer> <silent> ,fr :call Vjde_fix_throws()
vnoremap <buffer> <silent> ,js :call Vjde_sort_import()
vnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jc :call VjdeGenerateConstructor()
nnoremap <buffer> <silent> ,jg :call Vjde_get_set()
nnoremap <buffer> <silent> ,je :call Vjde_ext_import()
inoremap <buffer> ® =VjdeIabCompletionPUM()
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal cindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=j1
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=sO:*\ -,mO:*\ \ ,exO:*/,s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=//%s
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=VjdeCompletionFun0
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
setlocal nocursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != 'java'
setlocal filetype=java
endif
setlocal foldcolumn=0
setlocal foldenable
setlocal foldexpr=0
setlocal foldignore=#
setlocal foldlevel=0
setlocal foldmarker={{{,}}}
setlocal foldmethod=manual
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=croql
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=0
setlocal imsearch=0
setlocal include=
setlocal includeexpr=substitute(v:fname,'\\.','/','g')
setlocal indentexpr=GetJavaIndent()
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e,0=extends,0=implements
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=javacomplete#Complete
setlocal path=
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal smartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=fr,en
setlocal statusline=
setlocal suffixesadd=.java
setlocal swapfile
setlocal synmaxcol=3000
if &syntax != 'java'
setlocal syntax=java
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal noundofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
silent! normal! zE
let s:l = 15 - ((14 * winheight(0) + 27) / 55)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
15
normal! 01l
tabedit com/mojang/ld22/level/Level.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
argglobal
nnoremap <buffer> <silent> ,jd :call VjdeGetDocUnderCursor()
nnoremap <buffer> <silent> ,as :call VjdeAppendTemplate("Singleton")
nnoremap <buffer> <silent> ,ii :call VjdeImplementInner()
nnoremap <buffer> <silent> ,oi :call Vjde_override(1)
nnoremap <buffer> <silent> ,oe :call Vjde_override(0)
vnoremap <buffer> <silent> ,en :call Vjde_rft_const()
nnoremap <buffer> <silent> ,ep :call Vjde_rft_arg()
nnoremap <buffer> <silent> ,em :call Vjde_rft_var(1)
nnoremap <buffer> <silent> ,el :call Vjde_rft_var(2)
nnoremap <buffer> <silent> ,ri :call Vjde_remove_imports()
nnoremap <buffer> <silent> ,ai :call Vjde_fix_import1()
nnoremap <buffer> <silent> ,fi :call Vjde_fix_import()
nnoremap <buffer> <silent> ,ft :call Vjde_fix_try()
nnoremap <buffer> <silent> ,fr :call Vjde_fix_throws()
vnoremap <buffer> <silent> ,js :call Vjde_sort_import()
vnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jt :call Vjde_surround_try()
nnoremap <buffer> <silent> ,jc :call VjdeGenerateConstructor()
nnoremap <buffer> <silent> ,jg :call Vjde_get_set()
nnoremap <buffer> <silent> ,je :call Vjde_ext_import()
inoremap <buffer> ® =VjdeIabCompletionPUM()
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal cindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=j1
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=sO:*\ -,mO:*\ \ ,exO:*/,s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=//%s
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=VjdeCompletionFun0
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
setlocal nocursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != 'java'
setlocal filetype=java
endif
setlocal foldcolumn=0
setlocal foldenable
setlocal foldexpr=0
setlocal foldignore=#
setlocal foldlevel=0
setlocal foldmarker={{{,}}}
setlocal foldmethod=manual
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=croql
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=0
setlocal imsearch=0
setlocal include=
setlocal includeexpr=substitute(v:fname,'\\.','/','g')
setlocal indentexpr=GetJavaIndent()
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e,0=extends,0=implements
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=javacomplete#Complete
setlocal path=.,/usr/include,,
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal smartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=fr,en
setlocal statusline=
setlocal suffixesadd=.java
setlocal swapfile
setlocal synmaxcol=3000
if &syntax != 'java'
setlocal syntax=java
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal noundofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
silent! normal! zE
let s:l = 8 - ((7 * winheight(0) + 27) / 55)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
8
normal! 016l
tabnext 3
if exists('s:wipebuf')
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20 shortmess=filnxtToO
let s:sx = expand("<sfile>:p:r")."x.vim"
if file_readable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &so = s:so_save | let &siso = s:siso_save
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
