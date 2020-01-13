#!/bin/bash
set -ex

cd `dirname $0`

ansible-playbook -i ansible/inventory.yaml ansible/deploy-playbook.yaml \
#   --ask-pass \
#    --extra-vars "ansible_ssh_pass=" \
