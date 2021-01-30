#!/usr/bin/env bash
# Python installed with brew
#
export PATH=/usr/local/opt/sqlite/bin:/usr/local/opt/openssl/bin:/usr/local/opt/python/libexec/bin:/Users/jan/bin:$PATH
### python pip
export PATH=/Users/jan/venv/bin:$PATH
#
# Google cloud
# source /usr/local/Caskroom/google-cloud-sdk/latest/google-cloud-sdk/completion.zsh.inc
# source /usr/local/Caskroom/google-cloud-sdk/latest/google-cloud-sdk/path.zsh.inc

## NVM
# export NVM_DIR="$HOME/.nvm"
# . "/usr/local/opt/nvm/nvm.sh"
nvm use 12.13.1

# Azure
source '/Users/jan/lib/azure-cli/az.completion'

### aspectj
# export PATH=$PATH:/Users/jan/dev/aspectj1.9/bin
# export CLASSPATH=/Users/jan/dev/aspectj1.9/lib/aspectjrt.jar

### Java aliases
alias j11="sdk use java 11.0.2-open"
alias j8="sdk use java  8.0.202-zulu"

### thefuck
eval $(thefuck --alias)
alias f="fuck"

### enscript
alias p="enscript -fCourier10"

git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"

export GPG_TTY=$(tty) 

#THIS MUST BE AT THE END OF THE FILE FOR SDKMAN TO WORK!!!
export SDKMAN_DIR="/Users/jan/.sdkman"
[[ -s "/Users/jan/.sdkman/bin/sdkman-init.sh" ]] && source "/Users/jan/.sdkman/bin/sdkman-init.sh"
